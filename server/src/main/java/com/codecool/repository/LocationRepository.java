package com.codecool.repository;

import com.codecool.model.location.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Integer> {
  Location getLocationById(long id);

  int deleteLocationById(long id);
}
