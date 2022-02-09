package com.moneymie.carproject.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiException {

    private String message;

    private boolean success;

    private HttpStatus httpStatus;

    private LocalDateTime timestamp;
}
