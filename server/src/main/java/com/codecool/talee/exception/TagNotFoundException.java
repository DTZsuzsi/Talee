package com.codecool.talee.exception;

public class TagNotFoundException extends RuntimeException {
  public TagNotFoundException(String name) {
    super("Tag with name " + name + " not found");
  }
}