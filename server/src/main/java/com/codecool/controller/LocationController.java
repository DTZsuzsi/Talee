package com.codecool.controller;

import com.codecool.DTO.locationDTO.LocationDTO;
import com.codecool.DTO.locationDTO.NewLocationDTO;
import com.codecool.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/locations")

public class LocationController {
  private final LocationService locationService;

  @Autowired
  public LocationController(LocationService locationService) {
    this.locationService = locationService;
  }

  //TODO deleteapping, patchmapping, postmapping

  @GetMapping
  public Set<LocationDTO> getLocations() {
    return locationService.getAllLocations();
  }

  @GetMapping("/{id}")
  public LocationDTO getLocationById(@PathVariable int id) {
    return locationService.getLocationById(id);
  }

  @PostMapping
  public int addLocation(@RequestBody NewLocationDTO location) {
    return locationService.addLocation(location);
  }
}
