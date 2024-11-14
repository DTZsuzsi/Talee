package com.codecool.DTO.locationDTO;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record OpeningHoursDTO(long id, DayOfWeek day, LocalTime openingTime, LocalTime closingTime, LocationDTO location) {
}
