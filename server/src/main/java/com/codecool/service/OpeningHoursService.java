package com.codecool.service;

import com.codecool.DTO.locationDTO.NewOpeningHoursDTO;
import com.codecool.DTO.locationDTO.OpeningHoursDTO;
import com.codecool.exceptions.LocationNotFoundException;
import com.codecool.exceptions.OpeningHoursNotFoundException;
import com.codecool.model.location.Location;
import com.codecool.model.location.OpeningHours;
import com.codecool.repository.LocationRepository;
import com.codecool.repository.OpeningHoursRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.List;

@Service
public class OpeningHoursService {
  private final OpeningHoursRepository openingHoursRepository;
  private final LocationRepository locationRepository;

  @Autowired
  public OpeningHoursService(OpeningHoursRepository openingHoursRepository, LocationRepository locationRepository) {
    this.openingHoursRepository = openingHoursRepository;
    this.locationRepository = locationRepository;
  }

  public List<OpeningHoursDTO> getAllOpeningHoursByLocationId(long locationId) {
    List<OpeningHours> openingHours = openingHoursRepository.findByLocationId(locationId);
    return openingHours.stream().map(OpeningHoursService::createOpeningHoursDTO).toList();
  }

  private static OpeningHoursDTO createOpeningHoursDTO(OpeningHours openingHoursPerDay) {
    return new OpeningHoursDTO(
            openingHoursPerDay.getId(),
            openingHoursPerDay.getDayOfWeek(),
            openingHoursPerDay.getOpeningTime(),
            openingHoursPerDay.getClosingTime()
    );
  }

  @Transactional
  public long addNewOpeningHours(NewOpeningHoursDTO openingHoursPerDay) {
    OpeningHours newOpeningHoursPerDay = new OpeningHours();
    //TODO if existing location - source from DB -> throw exception if not found
//    if (openingHoursPerDay.location() != null) {
      Location existingLocation = locationRepository.findById(openingHoursPerDay.location().id())
              .orElseThrow(() -> new LocationNotFoundException(openingHoursPerDay.location().id())
              );
      newOpeningHoursPerDay.setDayOfWeek(openingHoursPerDay.day());
      newOpeningHoursPerDay.setOpeningTime(openingHoursPerDay.openingTime());
      newOpeningHoursPerDay.setClosingTime(openingHoursPerDay.closingTime());
      newOpeningHoursPerDay.setLocation(existingLocation);
      return openingHoursRepository.save(newOpeningHoursPerDay).getId();
//    }

  }

  @Transactional
  public boolean deleteOpeningHoursByLocationId(long locationId) {
    return openingHoursRepository.deleteByLocationId(locationId) != -1;
  }

//  public OpeningHoursDTO getOpeningHoursByLocationIdAndDay(long openingHoursId) {
//    OpeningHours existingOpeningHoursPerDay = openingHoursRepository.findById(openingHoursId)
//            .orElseThrow(() -> new OpeningHoursNotFoundException(openingHoursId));
//    return createOpeningHoursDTO(existingOpeningHoursPerDay);
//  }

//  public boolean updateOpeningHours(long locationId, OpeningHoursDTO openingHoursPerDay) {
//
//  }

}
