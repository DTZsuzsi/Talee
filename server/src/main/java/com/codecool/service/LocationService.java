package com.codecool.service;


import com.codecool.DTO.location.LocationDTO;
import com.codecool.DTO.location.NewLocationDTO;
import com.codecool.mapper.LocationMapper;
import com.codecool.model.locations.Location;
import com.codecool.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocationService {
  private final LocationRepository locationRepository;
  private final LocationMapper locationMapper = LocationMapper.INSTANCE;


  @Autowired
  public LocationService(LocationRepository locationRepository) {
    this.locationRepository = locationRepository;
  }

  public List<LocationDTO> getAllLocations() {
    List<Location> locations = locationRepository.findAll();

    return locations.stream()
            .map(locationMapper::locationToLocationDTO)
            .toList();
  }

  public LocationDTO getLocationById(int id) {
    Location location = locationRepository.getLocationById(id);
    return locationMapper.locationToLocationDTO(location);
  }

  public int addLocation(NewLocationDTO location) {
    Location newLocation = locationMapper.newLocationDTOToLocation(location);
    return locationRepository.save(newLocation).getId();
  }

  @Transactional
  public int deleteLocation(int id) {
    return locationRepository.deleteLocationById(id);
  }

  //TODO implement rest of CRUD operations - delete, patch


}
