package com.codecool.talee.DTO.location;

import java.time.DayOfWeek;
import java.time.LocalTime;

public record OpeningHoursDTO(long id, DayOfWeek day, LocalTime openingTime, LocalTime closingTime) {
}
