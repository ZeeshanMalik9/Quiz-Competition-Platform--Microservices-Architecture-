package com.proj.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // Log the exception (optional)
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("An error occurred: " + ex.getMessage());
    }
}