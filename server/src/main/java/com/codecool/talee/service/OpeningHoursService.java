package com.codecool.talee.service;

import com.codecool.talee.DTO.location.NewOpeningHoursDTO;
import com.codecool.talee.exception.EntityNotFoundException;
import com.codecool.talee.model.locations.Location;
import com.codecool.talee.model.locations.OpeningHours;
import com.codecool.talee.repository.LocationRepository;
import com.codecool.talee.repository.OpeningHoursRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpeningHoursService {
  private final OpeningHoursRepository openingHoursRepository;
  private final LocationRepository locationRepository;

  @Autowired
  public OpeningHoursService(OpeningHoursRepository openingHoursRepository, LocationRepository locationRepository) {
    this.openingHoursRepository = openingHoursRepository;
    this.locationRepository = locationRepository;
  }

  @Transactional
  public long addNewOpeningHours(NewOpeningHoursDTO openingHoursPerDay) {
    OpeningHours newOpeningHoursPerDay = new OpeningHours();
    if (openingHoursPerDay.location() != null) {
      Location existingLocation = locationRepository.findById(openingHoursPerDay.location().id())
              .orElseThrow(() -> new EntityNotFoundException("Location", openingHoursPerDay.location().id())
              );
      newOpeningHoursPerDay.setDayOfWeek(openingHoursPerDay.day());
      newOpeningHoursPerDay.setOpeningTime(openingHoursPerDay.openingTime());
      newOpeningHoursPerDay.setClosingTime(openingHoursPerDay.closingTime());
      newOpeningHoursPerDay.setLocation(existingLocation);
    }
    return openingHoursRepository.save(newOpeningHoursPerDay).getId();
  }

  @Transactional
  public boolean deleteOpeningHoursByLocationId(long locationId) {
    return openingHoursRepository.deleteByLocationId(locationId) != -1;
  }
}
