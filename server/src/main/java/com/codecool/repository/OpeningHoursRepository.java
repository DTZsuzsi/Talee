package com.codecool.repository;

import com.codecool.model.location.OpeningHours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpeningHoursRepository extends JpaRepository<OpeningHours, Long> {
}
