
package com.codecool.controller;

import com.codecool.DTO.location.LocationDTO;
import com.codecool.DTO.location.NewLocationDTO;
import com.codecool.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/locations")
//@CrossOrigin(origins = "http://localhost:5173")
public class LocationController {
  private final LocationService locationService;

  @Autowired
  public LocationController(LocationService locationService) {
    this.locationService = locationService;
  }

  //TODO deleteapping, patchmapping

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

  @DeleteMapping("/{id}")
  public int deleteLocation(@PathVariable int id) {
    return locationService.deleteLocation(id);
  }

//  @PatchMapping("/{id}")
//  public boolean updateLocation(@PathVariable int id, @RequestBody LocationDTO location) {
//    return locationService.updateLocation(location);
//  }
}

