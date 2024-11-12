package com.codecool.exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(long userId){
        super("User with id " + userId + " not found");
    }
}
