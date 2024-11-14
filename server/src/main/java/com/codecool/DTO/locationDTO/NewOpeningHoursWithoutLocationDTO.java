package com.codecool.DTO.locationDTO;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record NewOpeningHoursWithoutLocationDTO(DayOfWeek day, LocalTime openingTime, LocalTime closingTime) {
}
