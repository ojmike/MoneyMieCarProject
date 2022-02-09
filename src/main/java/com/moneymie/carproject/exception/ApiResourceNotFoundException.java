package com.moneymie.carproject.exception;

public class ApiResourceNotFoundException extends RuntimeException{

    public ApiResourceNotFoundException(String message) {
        super(message);
    }

}
