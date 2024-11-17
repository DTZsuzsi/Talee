package com.codecool.repository;

import com.codecool.model.locations.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    long deleteLocationById(long id);
}
