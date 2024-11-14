package com.codecool.DTO.locationDTO;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record NewOpeningHoursDTO(DayOfWeek day, LocalTime openingTime, LocalTime closingTime, LocationWithoutOpeningHoursDTO location) {
}
