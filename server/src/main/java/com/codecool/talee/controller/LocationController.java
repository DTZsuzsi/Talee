
package com.codecool.talee.controller;

import com.codecool.talee.DTO.location.LocationDTO;
import com.codecool.talee.DTO.location.NewLocationDTO;
import com.codecool.talee.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {
  private final LocationService locationService;

  @Autowired
  public LocationController(LocationService locationService) {
    this.locationService = locationService;
  }

  @GetMapping("/all")
  public List<LocationDTO> getLocations() {
    return locationService.getAllLocations();
  }

  @GetMapping("/{id}")
  public LocationDTO getLocationById(@PathVariable long id) {
    return locationService.getLocationById(id);
  }

  @PostMapping
  @PreAuthorize("hasRole('USER')")
  public long addLocation(@RequestBody NewLocationDTO location, @RequestHeader (name = "Authorization") String token) {
    if (token.startsWith("Bearer ")) {
      token = token.substring(7);
    }
    return locationService.addLocation(location, token);
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('LOCATION_OWNER')")
  public long deleteLocation(@PathVariable long id) {
    System.out.println("deleting");return locationService.deleteLocation(id);
  }

  @PatchMapping
  @PreAuthorize("hasRole('LOCATION_OWNER')")
  public boolean updateLocation(@RequestBody LocationDTO location) {
    return locationService.updateLocation(location);
  }

  

  @GetMapping("/tagsfilter/{tagName}")
  public List<LocationDTO> getLocationsByTagName(@PathVariable String tagName) {
    return locationService.getLocationsByTag(tagName);
  }
}

