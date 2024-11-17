package com.codecool.exceptions;

public class OpeningHoursNotFoundException extends RuntimeException {
  public OpeningHoursNotFoundException(String message) {
    super(message);
  }
}
