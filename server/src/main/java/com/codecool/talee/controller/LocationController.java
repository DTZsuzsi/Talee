
package com.codecool.talee.controller;

import com.codecool.talee.DTO.location.LocationDTO;
import com.codecool.talee.DTO.location.NewLocationDTO;
import com.codecool.talee.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  public ResponseEntity<String> createLocation(@RequestBody NewLocationDTO location, @RequestHeader (name = "Authorization") String token) {
    if (token == null || !token.startsWith("Bearer ")) {
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or missing Authorization header");
    }

    String jwtToken = token.substring(7);
    boolean success = locationService.createLocation(location, jwtToken);

    return ResponseEntity.ok(success ? "true" : "false");
  }

  @DeleteMapping("/{id}")
  @PreAuthorize("hasRole('LOCATION_OWNER')")
  public boolean deleteLocation(@PathVariable long id) {
   return locationService.deleteLocation(id);
  }

  @PatchMapping
  @PreAuthorize("hasRole('LOCATION_OWNER')")
  public long updateLocation(@RequestBody LocationDTO location) {
    return locationService.updateLocation(location);
  }

  

  @GetMapping("/tagsfilter/{tagName}")
  public List<LocationDTO> getLocationsByTagName(@PathVariable String tagName) {
    return locationService.getLocationsByTag(tagName);
  }
}

