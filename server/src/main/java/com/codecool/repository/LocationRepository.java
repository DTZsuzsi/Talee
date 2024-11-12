package com.codecool.repository;

import com.codecool.model.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Integer> {
  Location getLocationById(int id);
}
