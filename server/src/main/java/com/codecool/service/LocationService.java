package com.codecool.service;


import com.codecool.DTO.locationDTO.*;
import com.codecool.exceptions.LocationNotFoundException;
import com.codecool.model.location.Location;
import com.codecool.model.location.OpeningHours;
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

  @Autowired
  public LocationService(LocationRepository locationRepository, OpeningHoursService openingHoursService) {
    this.locationRepository = locationRepository;
    this.openingHoursService = openingHoursService;
  }

  public Set<LocationDTO> getAllLocations() {
    Set<Location> locations = new HashSet<>(locationRepository.findAll());

    return locations.stream().map(LocationService::createLocationDTO).collect(Collectors.toSet());
  }

  public LocationDTO getLocationById(long id) {
    return locationRepository.findById(id)
            .map(LocationService::createLocationDTO)
            .orElseThrow(() -> new LocationNotFoundException(id));
  }

  private static LocationDTO createLocationDTO(Location location) {
    return new LocationDTO(location.getId(),
            location.getName(),
            location.getAddress(),
            location.getPhone(),
            location.getEmail(),
            location.getDescription(),
            location.getAdminUser(),
            location.getOpeningHours().stream().map(LocationService::createOpeningHoursDTO).collect(Collectors.toList()));
  }

  private static OpeningHoursDTO createOpeningHoursDTO(OpeningHours openingHoursPerDay) {
    return new OpeningHoursDTO(
            openingHoursPerDay.getId(),
            openingHoursPerDay.getDayOfWeek(),
            openingHoursPerDay.getOpeningTime(),
            openingHoursPerDay.getClosingTime()
    );
  }

  public long addLocation(NewLocationDTO location) {
    //TODO check if there is already a location with that name and specific other details? (eg. address),
    // after saving the location call addOpeningHours method to add the openinghours
    Location newLocation = new Location();
    newLocation.setName(location.name());
    newLocation.setAddress(location.address());
    newLocation.setPhone(location.phone());
    newLocation.setEmail(location.email());
    newLocation.setDescription(location.description());
    newLocation.setAdminUser(location.adminUser());
    Location savedLocation = locationRepository.save(newLocation);

    for (NewOpeningHoursWithoutLocationDTO newOpeningHours : location.openingHours()) {
//      OpeningHours newOpeningHours = new OpeningHours();
//      newOpeningHours.setDayOfWeek(newOpeningHours.getDayOfWeek());
//      newOpeningHours.setOpeningTime(newOpeningHoursDTO.openingTime());
//      newOpeningHours.setClosingTime(newOpeningHoursDTO.closingTime());
//      newOpeningHours.setLocation(savedLocation);
//      savedLocation.addOpeningHours(newOpeningHours);
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
    Location existingLocation = locationRepository.findById(location.id())
            .orElseThrow(() -> new LocationNotFoundException(location.id()));

    existingLocation.setName(location.name());
    existingLocation.setAddress(location.address());
    existingLocation.setPhone(location.phone());
    existingLocation.setEmail(location.email());
    existingLocation.setDescription(location.description());
    existingLocation.setAdminUser(location.adminUser());
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

  private static NewOpeningHoursDTO createNewOpeningHoursDTO(OpeningHoursDTO newHours, Location existingLocation) {
    LocationWithoutOpeningHoursDTO locationWithoutOpeningHoursDTO = createLocationWithoutOpeningHoursDTO(existingLocation);

    return new NewOpeningHoursDTO(
            newHours.day(),
            newHours.openingTime(),
            newHours.closingTime(),
            locationWithoutOpeningHoursDTO
    );
  }

  private static LocationWithoutOpeningHoursDTO createLocationWithoutOpeningHoursDTO(Location existingLocation) {
    return new LocationWithoutOpeningHoursDTO(
            existingLocation.getId(),
            existingLocation.getName(),
            existingLocation.getAddress(),
            existingLocation.getPhone(),
            existingLocation.getEmail(),
            existingLocation.getDescription(),
            existingLocation.getAdminUser()
    );
  }

}
