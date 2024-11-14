package com.codecool.exceptions;

public class LocationNotFoundException extends RuntimeException {
  public LocationNotFoundException(long locationId) {
    super("Location with id " + locationId + " not found");
  }
}
