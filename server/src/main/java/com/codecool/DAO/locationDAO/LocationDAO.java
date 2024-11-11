package com.codecool.DAO.locationDAO;

import com.codecool.DTO.locationDTO.NewLocationDTO;
import com.codecool.model.location.Location;

import java.util.Set;

public interface LocationDAO {
  Set<Location> getAllLocations();
  Location getLocationById(int id);
  int addLocation(NewLocationDTO location);
//  boolean updateLocation(LocationDTO location);
//  boolean deleteLocation(int id);
}
