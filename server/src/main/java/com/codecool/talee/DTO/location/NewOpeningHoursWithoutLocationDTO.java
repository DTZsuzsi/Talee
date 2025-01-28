package com.codecool.talee.DTO.location;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record NewOpeningHoursWithoutLocationDTO(DayOfWeek day, LocalTime openingTime, LocalTime closingTime) {
}
