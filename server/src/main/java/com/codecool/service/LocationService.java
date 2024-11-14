package com.codecool.service;


import com.codecool.DTO.locationDTO.LocationDTO;
import com.codecool.DTO.locationDTO.NewLocationDTO;
import com.codecool.DTO.locationDTO.OpeningHoursDTO;
import com.codecool.exceptions.LocationNotFoundException;
import com.codecool.model.location.Location;
import com.codecool.model.location.OpeningHours;
import com.codecool.repository.LocationRepository;
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

  @Autowired
  public LocationService(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
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
    Location newLocation = new Location();
    newLocation.setName(location.name());
    newLocation.setAddress(location.address());
    newLocation.setPhone(location.phone());
    newLocation.setEmail(location.email());
    newLocation.setDescription(location.description());
    newLocation.setAdminUser(location.adminUser());

    return locationRepository.save(newLocation).getId();
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
    return locationRepository.save(existingLocation).getId() != 0;
  }



}
