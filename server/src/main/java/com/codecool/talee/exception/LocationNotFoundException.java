package com.codecool.talee.exception;

public class LocationNotFoundException extends RuntimeException {
  public LocationNotFoundException(long locationId) {
    super("Location with id " + locationId + " not found");
  }
}