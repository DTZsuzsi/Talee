package com.codecool.service;

import com.codecool.repository.OpeningHoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpeningHoursService {
  private final OpeningHoursRepository openingHoursRepository;

  @Autowired
  public OpeningHoursService(OpeningHoursRepository openingHoursRepository) {
    this.openingHoursRepository = openingHoursRepository;
  }


}
