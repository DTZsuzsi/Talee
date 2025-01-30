package com.codecool.talee.service;


import com.codecool.talee.exception.EntityNotFoundException;
import com.codecool.talee.DTO.location.LocationDTO;
import com.codecool.talee.DTO.location.NewLocationDTO;
import com.codecool.talee.DTO.location.NewOpeningHoursDTO;
import com.codecool.talee.DTO.location.OpeningHoursDTO;
import com.codecool.talee.mapper.*;
import com.codecool.talee.model.locations.Location;
import com.codecool.talee.model.tags.Tag;
import com.codecool.talee.model.users.Role;
import com.codecool.talee.model.users.UserEntity;
import com.codecool.talee.repository.LocationRepository;
import com.codecool.talee.repository.RoleRepository;
import com.codecool.talee.repository.TagRepository;
import com.codecool.talee.repository.UserRepository;
import com.codecool.talee.security.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LocationService {


    private final LocationRepository locationRepository;
    private final OpeningHoursService openingHoursService;
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final JWTUtils jwtUtils;
    private final UserRepository userRepository;
    private final TagRepository tagRepository;

    private final LocationMapper locationMapper = LocationMapper.INSTANCE;
    private final UserMapper userMapper = UserMapper.INSTANCE;
    private final TagMapper tagMapper = TagMapper.INSTANCE;
    private final EventMapper eventMapper = EventMapper.INSTANCE;
    private final OpeningHoursMapper openingHoursMapper = OpeningHoursMapper.INSTANCE;

    @Autowired
    public LocationService(LocationRepository locationRepository, OpeningHoursService openingHoursService,
                           UserService userService,
                           RoleRepository roleRepository, JWTUtils jwtUtils, UserRepository userRepository,
                           TagRepository tagRepository) {
        this.locationRepository = locationRepository;
        this.openingHoursService = openingHoursService;
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.jwtUtils = jwtUtils;
        this.userRepository = userRepository;
        this.tagRepository = tagRepository;
    }

    public List<LocationDTO> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(locationMapper::locationToLocationDTO)
                .toList();
    }

    public List<LocationDTO> getLocationsByTag(String tagName) {
        Tag tag = tagRepository.findByName(tagName)
                .orElseThrow(() -> new RuntimeException("Tag not found: " + tagName));

        return locationRepository.findAllByTagsContaining(tag).stream()
                .map(locationMapper::locationToLocationDTO)
                .toList();
    }

  public LocationDTO getLocationById(long id) {
    Location location = locationRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("Location", id));

        return locationMapper.locationToLocationDTO(location);
    }

    public boolean createLocation(NewLocationDTO location, String token) {
        if (locationRepository.findLocationByNameAndAddress(location.name(), location.address()) != null) {
            throw new RuntimeException("Location already exists.");
        }

        Location newLocation = locationMapper.newLocationDTOToLocation(location);
        UserEntity currentUser = getCurrentUserFromToken(token);

        assignRoleToUser(currentUser, "ROLE_LOCATION_OWNER");
        newLocation.setAdminUser(currentUser);

        Location savedLocation = locationRepository.save(newLocation);

        location.openingHours().stream()
                .map(openingHoursMapper::openingHoursToNewOpeningHoursDTO)
                .forEach(openingHoursService::addNewOpeningHours);

        return savedLocation.getId()>0;
    }

    @Transactional
    public boolean deleteLocation(long id) {
        return locationRepository.deleteLocationById(id);
    }

    public long updateLocation(LocationDTO location) {
        Location existingLocation = locationRepository.findById(location.id())
                .orElseThrow(() -> new RuntimeException("Location not found."));

        updateLocationDetails(existingLocation, location);
        updateOpeningHours(existingLocation, location.openingHours());

        return locationRepository.save(existingLocation).getId();
    }

    private void updateLocationDetails(Location existingLocation, LocationDTO locationDTO) {
        existingLocation.setName(locationDTO.name());
        existingLocation.setTags(locationDTO.tags().stream()
                .map(tagMapper::tagDTOToTag)
                .collect(Collectors.toSet()));
        existingLocation.setAddress(locationDTO.address());
        existingLocation.setPhone(locationDTO.phone());
        existingLocation.setEmail(locationDTO.email());
        existingLocation.setDescription(locationDTO.description());
    }

    private void updateOpeningHours(Location existingLocation, List<OpeningHoursDTO> updatedOpeningHours) {
        if (updatedOpeningHours.isEmpty()) {
            openingHoursService.deleteOpeningHoursByLocationId(existingLocation.getId());
        } else {
            for (OpeningHoursDTO updatedHours : updatedOpeningHours) {
                existingLocation.getOpeningHours().stream()
                        .filter(hour -> hour.getDayOfWeek().equals(updatedHours.day()))
                        .findFirst()
                        .ifPresentOrElse(
                                existingHours -> {
                                    existingHours.setOpeningTime(updatedHours.openingTime());
                                    existingHours.setClosingTime(updatedHours.closingTime());
                                },
                                () -> openingHoursService.addNewOpeningHours(
                                        createNewOpeningHoursDTO(updatedHours, existingLocation)));
            }
        }
    }

    private NewOpeningHoursDTO createNewOpeningHoursDTO(OpeningHoursDTO newHours, Location existingLocation) {
        return new NewOpeningHoursDTO(
                newHours.day(),
                newHours.openingTime(),
                newHours.closingTime(),
                locationMapper.locationToLocationWithoutOpeningHoursDTO(existingLocation)
        );
    }

    private UserEntity getCurrentUserFromToken(String token) {
        String username = jwtUtils.getUsernameFromJwtToken(token);
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found."));
    }

    private void assignRoleToUser(UserEntity user, String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role not found: " + roleName));

        // Create a mutable copy of the roles
        Set<Role> updatedRoles = new HashSet<>(user.getRoles());
        updatedRoles.add(role);

        // Set the updated roles back to the user
        user.setRoles(updatedRoles);

        // Update the user using the userService
        userService.modifyUser(userMapper.userToUserDTO(user));

    }

}