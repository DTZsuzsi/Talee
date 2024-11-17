package com.codecool.controller;

import com.codecool.exceptions.LocationNotFoundException;
import com.codecool.exceptions.OpeningHoursNotFoundException;
import com.codecool.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleUserNotFoundException(UserNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(LocationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleLocationNotFoundException(LocationNotFoundException ex) {
        return ex.getMessage();
    }

    @ExceptionHandler(OpeningHoursNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleOpeningHoursNotFoundException(OpeningHoursNotFoundException ex) {return ex.getMessage();}
}

