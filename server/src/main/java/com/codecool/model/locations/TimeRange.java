package com.codecool.model.locations;

import java.time.LocalTime;

public record TimeRange(LocalTime openingTime, LocalTime closingTime) {
}
