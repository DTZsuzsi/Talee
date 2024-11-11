package com.codecool.model.location;

import java.time.LocalTime;

public record TimeRange(LocalTime openingTime, LocalTime closingTime) {
}
