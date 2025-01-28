package com.codecool.talee.repository;

import com.codecool.talee.model.locations.OpeningHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpeningHoursRepository extends JpaRepository<OpeningHours, Long> {
  long deleteByLocationId(Long locationId);
}
