
package com.codecool.controller;

import com.codecool.DTO.location.LocationDTO;
import com.codecool.DTO.location.NewLocationDTO;
import com.codecool.DTO.tag.TaginFrontendDTO;
import com.codecool.service.LocationService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

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
  public LocationDTO getLocationById(@PathVariable int id) {
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
    return locationService.deleteLocation(id);
  }

  @PatchMapping
  @PreAuthorize("hasRole('LOCATION_OWNER')")
  public boolean updateLocation(@RequestBody LocationDTO location) {
    return locationService.updateLocation(location);
  }


  @PostMapping("/{locationId}")
  @PreAuthorize("hasRole('LOCATION_OWNER')")
  public boolean addTagToLocation(@PathVariable long locationId, @RequestBody TaginFrontendDTO taginFrontendDTO) {
    return locationService.addTagToLocation(locationId, taginFrontendDTO);
  }

  @DeleteMapping("/tag/{locationId}")
  @PreAuthorize("hasRole('LOCATION_OWNER')")
  public boolean deleteTag(@PathVariable long locationId, @RequestParam int tagId) {
    return locationService.deleteTagFromLocation(locationId, tagId);
  }
}

