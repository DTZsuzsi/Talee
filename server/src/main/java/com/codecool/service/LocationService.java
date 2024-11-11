package com.codecool.service;

import com.codecool.DAO.locationDAO.LocationDAO;
import com.codecool.DTO.locationDTO.LocationDTO;
import com.codecool.DTO.locationDTO.NewLocationDTO;
import com.codecool.model.location.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class LocationService {
  private final LocationDAO locationDAO;

  @Autowired
  public LocationService(LocationDAO locationDAO) {
    this.locationDAO = locationDAO;
  }

  public Set<LocationDTO> getAllLocations() {
    Set<Location> locations = locationDAO.getAllLocations();

    return locations.stream().map(location -> new LocationDTO(
            location.getId(),
            location.getName(),
            location.getAddress(),
            location.getPhone(),
            location.getEmail(),
            location.getDescription()
    )).collect(Collectors.toSet());
  }

  public LocationDTO getLocationById(int id) {
    Location location = locationDAO.getLocationById(id);
    return new LocationDTO(location.getId(),
            location.getName(),
            location.getAddress(),
            location.getPhone(),
            location.getEmail(),
            location.getDescription());
  }

  public int addLocation(NewLocationDTO location) {
    return locationDAO.addLocation(location);
  }

  //TODO implement rest of CRUD operations
}
