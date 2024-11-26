
package com.codecool.controller;

import com.codecool.DTO.location.LocationDTO;
import com.codecool.DTO.location.NewLocationDTO;
import com.codecool.DTO.tag.TaginFrontendDTO;
import com.codecool.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
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

  @GetMapping
  public List<LocationDTO> getLocations() {
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


  @PostMapping("/{locationId}")
  public boolean addTagToLocation(@PathVariable long locationId, @RequestBody TaginFrontendDTO taginFrontendDTO) {
    return locationService.addTagToLocation(locationId, taginFrontendDTO);
  }

  @DeleteMapping("/tag/{locationId}")
  public boolean deleteTag(@PathVariable long locationId, @RequestParam int tagId) {
    return locationService.deleteTagFromLocation(locationId, tagId);
  }
}

