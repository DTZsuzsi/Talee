package com.codecool.talee.exception;

public class EventNotFoundException extends RuntimeException {
  public EventNotFoundException(long locationId) {
    super("Event with id " + locationId + " not found");
  }
}