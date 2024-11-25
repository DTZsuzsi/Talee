package com.codecool.DTO.location;

import com.codecool.DTO.location.LocationWithoutOpeningHoursDTO;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record NewOpeningHoursDTO(DayOfWeek day, LocalTime openingTime, LocalTime closingTime, LocationWithoutOpeningHoursDTO location) {
}
