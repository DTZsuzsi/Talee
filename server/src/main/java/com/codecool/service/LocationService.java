package com.codecool.service;


import com.codecool.DTO.event.EventDTO;
import com.codecool.DTO.location.*;
import com.codecool.DTO.tag.TaginFrontendDTO;
import com.codecool.DTO.user.UserDTO;
import com.codecool.exception.LocationNotFoundException;
import com.codecool.mapper.*;
import com.codecool.model.locations.Location;
import com.codecool.model.locations.OpeningHours;
import com.codecool.model.tags.Tag;
import com.codecool.model.users.Role;
import com.codecool.model.users.UserEntity;
import com.codecool.repository.*;
import com.codecool.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LocationService {
  private final LocationRepository locationRepository;
  private final OpeningHoursService openingHoursService;
  private final TagCategoryRepository tagCategoryRepository;
  private final UserService userService;
  private final RoleRepository roleRepository;
  private final LocationMapper locationMapper = LocationMapper.INSTANCE;
  private final UserMapper userMapper = UserMapper.INSTANCE;
  private final TagMapper tagMapper = TagMapper.INSTANCE;
  private final EventMapper eventMapper=EventMapper.INSTANCE;
  private final OpeningHoursMapper openingHoursMapper=OpeningHoursMapper.INSTANCE;
  private final JWTUtils jwtUtils;
  private final UserRepository userRepository;
  private final TagRepository tagRepository;


  @Autowired
  public LocationService(LocationRepository locationRepository, OpeningHoursService openingHoursService,
                         TagCategoryRepository tagCategoryRepository, UserService userService, RoleRepository roleRepository,
                         JWTUtils jwtUtils, UserRepository userRepository, TagRepository tagRepository) {
    this.locationRepository = locationRepository;
    this.openingHoursService = openingHoursService;
    this.tagCategoryRepository = tagCategoryRepository;
    this.userService = userService;
    this.roleRepository = roleRepository;
    this.jwtUtils = jwtUtils;
    this.userRepository = userRepository;
    this.tagRepository = tagRepository;
  }

  public List<LocationDTO> getAllLocations() {
    List<Location> locations = locationRepository.findAll();

    return locations.stream()
            .map(location -> getLocationById(location.getId()))
            .toList();
  }

  public List<LocationDTO> getLocationsByTag(String tagName){
    Tag tag=tagRepository.findByName(tagName);
    return locationRepository.findAllByTagsContaining(tag).stream().map(location -> getLocationById(location.getId())).collect(Collectors.toList());
  }
  public LocationDTO getLocationById(long id) {
    Location location = locationRepository.findById(id).orElseThrow(() -> new LocationNotFoundException(id));
    Set<EventDTO> events=location.getEvents().stream().map(eventMapper::eventToEventDTO).collect(Collectors.toSet());
    List<OpeningHoursDTO> openingHoursDTOs=location.getOpeningHours().stream().map(openingHoursMapper::openingHourstoOpeningHoursDTO).collect(Collectors.toList());
    Set<TaginFrontendDTO> tags=location.getTags().stream().map(tagMapper::tagToTaginFrontendDTO).collect(Collectors.toSet());
    LocationDTO locationDTO=new LocationDTO(id,location.getName(),location.getAddress(),location.getPhone(),location.getEmail(),location.getDescription(),
            userMapper.userToUserDTO(location.getAdminUser()),openingHoursDTOs,tags,location.getLatitude(),location.getLongitude(),events);

    return locationDTO;
  }

  //TODO check if there is already a location with that name and specific other details? (eg. address),
  // after saving the location call addOpeningHours method to add the openinghours
  public long addLocation(NewLocationDTO location, String token) {
    Location newLocation = locationMapper.newLocationDTOToLocation(location);
    String currentUserName = jwtUtils.getUsernameFromJwtToken(token);
    UserEntity currentUser = userRepository.findByUsername(currentUserName).get();
    Set<Role> currentUserRoles = currentUser.getRoles();
    Role locationOwnerRole = roleRepository.findByName("ROLE_LOCATION_OWNER").get();
    currentUserRoles.add(locationOwnerRole);
    currentUser.setRoles(currentUserRoles);
    newLocation.setAdminUser(currentUser);

    UserDTO updatedUserDTO = userMapper.userToUserDTO(currentUser);
    userService.modifyUser(updatedUserDTO);

    Location savedLocation = locationRepository.save(newLocation);

    for (NewOpeningHoursWithoutLocationDTO newOpeningHours : location.openingHours()) {
      LocationWithoutOpeningHoursDTO locationWithoutOpeningHoursDTO = createLocationWithoutOpeningHoursDTO(savedLocation);
      NewOpeningHoursDTO newOpeningHoursDTO = new NewOpeningHoursDTO(
              newOpeningHours.day(),
              newOpeningHours.openingTime(),
              newOpeningHours.closingTime(),
              locationWithoutOpeningHoursDTO
      );
      openingHoursService.addNewOpeningHours(newOpeningHoursDTO);
    }

    return savedLocation.getId();
  }

  @Transactional
  public long deleteLocation(long id) {
    return locationRepository.deleteLocationById(id);
  }


  public boolean updateLocation(LocationDTO location) {
    Location existingLocation = locationRepository.findById(location.id()).get();
       //     .orElseThrow(() -> new LocationNotFoundException(location.id()));

    Set<Tag> tags = location.tags().stream().map(tagMapper::tagInFrontendDTOsToTag).collect(Collectors.toSet());

    existingLocation.setName(location.name());
    existingLocation.setTags(tags);
    existingLocation.setAddress(location.address());
    existingLocation.setPhone(location.phone());
    existingLocation.setEmail(location.email());
    existingLocation.setDescription(location.description());
    List<OpeningHoursDTO> updatedOpeningHours = location.openingHours();

    List<OpeningHours> existingOpeningHours = existingLocation.getOpeningHours();

    if (updatedOpeningHours.isEmpty()) {
      return openingHoursService.deleteOpeningHoursByLocationId(existingLocation.getId());
    } else {
      for (OpeningHoursDTO updatedHours : updatedOpeningHours) {
        Optional<OpeningHours> existingOpeningHoursOpt = existingOpeningHours.stream()
                .filter(hour -> hour.getDayOfWeek().equals(updatedHours.day()))
                .findFirst();

        if (existingOpeningHoursOpt.isPresent()) {
          OpeningHours existingHours = existingOpeningHoursOpt.get();
          existingHours.setOpeningTime(updatedHours.openingTime());
          existingHours.setClosingTime(updatedHours.closingTime());
        } else {
          NewOpeningHoursDTO newOpeningHoursDTO = createNewOpeningHoursDTO(updatedHours, existingLocation);
          openingHoursService.addNewOpeningHours(newOpeningHoursDTO);
        }
      }
    }

    return locationRepository.save(existingLocation).getId() != 0;
  }

  private NewOpeningHoursDTO createNewOpeningHoursDTO(OpeningHoursDTO newHours, Location existingLocation) {
    LocationWithoutOpeningHoursDTO locationWithoutOpeningHoursDTO = createLocationWithoutOpeningHoursDTO(existingLocation);

    return new NewOpeningHoursDTO(
            newHours.day(),
            newHours.openingTime(),
            newHours.closingTime(),
            locationWithoutOpeningHoursDTO
    );
  }

  private LocationWithoutOpeningHoursDTO createLocationWithoutOpeningHoursDTO(Location existingLocation) {
    return new LocationWithoutOpeningHoursDTO(
            existingLocation.getId(),
            existingLocation.getName(),
            existingLocation.getAddress(),
            existingLocation.getPhone(),
            existingLocation.getEmail(),
            existingLocation.getDescription(),
            userMapper.userToUserDTO(existingLocation.getAdminUser()
                    ),existingLocation.getLatitude(), existingLocation.getLongitude()
    );
  }







}
