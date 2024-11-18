package com.codecool.service;

import com.codecool.DTO.location.NewOpeningHoursDTO;
import com.codecool.DTO.location.OpeningHoursDTO;
import com.codecool.exception.LocationNotFoundException;
import com.codecool.mapper.OpeningHoursMapper;
import com.codecool.model.locations.Location;
import com.codecool.model.locations.OpeningHours;
import com.codecool.repository.LocationRepository;
import com.codecool.repository.OpeningHoursRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class OpeningHoursService {
  private final OpeningHoursRepository openingHoursRepository;
  private final LocationRepository locationRepository;
  private final OpeningHoursMapper openingHoursMapper = OpeningHoursMapper.INSTANCE;

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

    //TODO if existing location - source from DB -> throw exception if not found
//    if (openingHoursPerDay.location() != null) {
      Location existingLocation = locationRepository.findById(openingHoursPerDay.location().id())
              .orElseThrow(() -> new LocationNotFoundException(openingHoursPerDay.location().id())
              );
    OpeningHours newOpeningHoursPerDay =
            openingHoursMapper.newOpeningHoursDTOToOpeningHours(openingHoursPerDay, existingLocation);
      return openingHoursRepository.save(newOpeningHoursPerDay).getId();
//    }

  }

  @Transactional
  public boolean deleteOpeningHoursByLocationId(long locationId) {
    return openingHoursRepository.deleteByLocationId(locationId) != -1;
  }


}
