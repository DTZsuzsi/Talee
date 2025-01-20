package com.codecool.repository;

import com.codecool.model.locations.OpeningHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OpeningHoursRepository extends JpaRepository<OpeningHours, Long> {
  List<OpeningHours> findByLocationId(Long locationId);
  long deleteByLocationId(Long locationId);
}
