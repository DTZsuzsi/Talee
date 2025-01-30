package com.codecool.talee.exception;

public class EntityNotFoundException extends RuntimeException {
  public EntityNotFoundException(String entity, long id) {
    super(entity + " with id " + id + " not found");
  }

  public EntityNotFoundException(String entity, String name) {
    super(entity + " with name " + name + " not found");
  }
}
