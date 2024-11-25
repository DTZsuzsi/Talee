package com.codecool.exception;

public class OpeningHoursNotFoundException extends RuntimeException {
  public OpeningHoursNotFoundException(long openingHoursId) {
    super("OpeningHours with id " + openingHoursId + " not found");
  }
}
