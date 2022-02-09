package com.moneymie.carproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.time.ZoneId;

@ControllerAdvice
@ResponseStatus
public class ApiExceptionHandler {

    @ExceptionHandler(value = {ApiBadRequestException.class})
    public ResponseEntity<Object> handleApiRequestException(ApiBadRequestException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(e.getMessage(),
                false, badRequest, LocalDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, badRequest);
    }

    @ExceptionHandler(value = {ApiResourceNotFoundException.class})
    public ResponseEntity<Object> handleApiResourceNotFoundException(ApiResourceNotFoundException e) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;

        ApiException apiException = new ApiException(e.getMessage(),
                false, notFound, LocalDateTime.now(ZoneId.of("Z")));
        

        return new ResponseEntity<>(apiException, notFound);
    }

    @ExceptionHandler(value = {ApiRequestUnauthorizedException.class})
    public ResponseEntity<Object> handleApiRequestUnauthorizedException(ApiResourceNotFoundException e) {
        HttpStatus notFound = HttpStatus.UNAUTHORIZED;

        ApiException apiException = new ApiException(e.getMessage(),
                false, notFound, LocalDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, notFound);
    }



}
