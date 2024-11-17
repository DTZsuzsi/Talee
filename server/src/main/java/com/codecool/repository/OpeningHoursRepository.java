package com.codecool.repository;

import com.codecool.model.location.OpeningHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;

@Repository
public interface OpeningHoursRepository extends JpaRepository<OpeningHours, Long> {
  List<OpeningHours> findByLocationId(Long locationId);
}
