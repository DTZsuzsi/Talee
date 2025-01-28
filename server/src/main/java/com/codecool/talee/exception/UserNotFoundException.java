package com.codecool.talee.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(long userId){
        super("User with id " + userId + " not found");
    }
    public UserNotFoundException(String name){
        super("User with name " + name + " not found");
    }
}
