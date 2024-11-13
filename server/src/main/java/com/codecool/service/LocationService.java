package com.codecool.service;


import com.codecool.DTO.locationDTO.LocationDTO;
import com.codecool.DTO.locationDTO.NewLocationDTO;
import com.codecool.model.location.Location;
import com.codecool.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LocationService {
//  private final LocationDAO locationDAO;
  private final LocationRepository locationRepository;

  @Autowired
  public LocationService(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }

  public Set<LocationDTO> getAllLocations() {
    Set<Location> locations = new HashSet<>(locationRepository.findAll());

    return locations.stream().map(location -> new LocationDTO(
            location.getId(),
            location.getName(),
            location.getAddress(),
            location.getPhone(),
            location.getEmail(),
            location.getDescription(),
            location.getAdminUser()
    )).collect(Collectors.toSet());
  }

  public LocationDTO getLocationById(int id) {
    Location location = locationRepository.getLocationById(id);
    return new LocationDTO(location.getId(),
            location.getName(),
            location.getAddress(),
            location.getPhone(),
            location.getEmail(),
            location.getDescription(),
            location.getAdminUser());
  }

  public int addLocation(NewLocationDTO location) {
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
  public int deleteLocation(int id) {
    return locationRepository.deleteLocationById(id);
  }

  //TODO implement rest of CRUD operations - delete, patch


}
