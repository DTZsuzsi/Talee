
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

  @GetMapping
  public Set<LocationDTO> getLocations() {
    return locationService.getAllLocations();
  }

  @GetMapping("/{id}")
  public LocationDTO getLocationById(@PathVariable int id) {
    return locationService.getLocationById(id);
  }

  @PostMapping
  public long addLocation(@RequestBody NewLocationDTO location) {
    return locationService.addLocation(location);
  }

  @DeleteMapping("/{id}")
  public long deleteLocation(@PathVariable long id) {
    return locationService.deleteLocation(id);
  }

  @PatchMapping
  public boolean updateLocation(@RequestBody LocationDTO location) {
    return locationService.updateLocation(location);
  }
}

