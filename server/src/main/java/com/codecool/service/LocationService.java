package com.codecool.service;


import com.codecool.DTO.location.*;
import com.codecool.exception.LocationNotFoundException;
import com.codecool.mapper.LocationMapper;
import com.codecool.mapper.UserMapper;
import com.codecool.model.locations.Location;
import com.codecool.model.locations.OpeningHours;
import com.codecool.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LocationService {
  private final LocationRepository locationRepository;
  private final OpeningHoursService openingHoursService;
  private final LocationMapper locationMapper = LocationMapper.INSTANCE;
  private final UserMapper userMapper = UserMapper.INSTANCE;


  @Autowired
  public LocationService(LocationRepository locationRepository, OpeningHoursService openingHoursService) {
    this.locationRepository = locationRepository;
    this.openingHoursService = openingHoursService;
  }

  public List<LocationDTO> getAllLocations() {
    List<Location> locations = locationRepository.findAll();

    return locations.stream()
            .map(this::createLocationDTO)
            .toList();
  }

  public LocationDTO getLocationById(long id) {
    Location location = locationRepository.findById(id).get();
    return createLocationDTO(location);
  }

  //TODO check if there is already a location with that name and specific other details? (eg. address),
  // after saving the location call addOpeningHours method to add the openinghours
  public long addLocation(NewLocationDTO location) {
    Location newLocation = locationMapper.newLocationDTOToLocation(location);
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

    existingLocation.setName(location.name());
    existingLocation.setAddress(location.address());
    existingLocation.setPhone(location.phone());
    existingLocation.setEmail(location.email());
    existingLocation.setDescription(location.description());
   // existingLocation.setAdminUser(location.adminUser());
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
            userMapper.userToUserDTO(existingLocation.getAdminUser())
    );
  }

  private LocationDTO createLocationDTO(Location location) {
    return new LocationDTO(location.getId(),
            location.getName(),
            location.getAddress(),
            location.getPhone(),
            location.getEmail(),
            location.getDescription(),
            userMapper.userToUserDTO(location.getAdminUser()),
            location.getOpeningHours().stream().map(this::createOpeningHoursDTO).collect(Collectors.toList()));
  }

  private OpeningHoursDTO createOpeningHoursDTO(OpeningHours openingHoursPerDay) {
    return new OpeningHoursDTO(
            openingHoursPerDay.getId(),
            openingHoursPerDay.getDayOfWeek(),
            openingHoursPerDay.getOpeningTime(),
            openingHoursPerDay.getClosingTime()
    );
  }

}
