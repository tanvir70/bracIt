package com.tanvir.blogpost.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<String> myResourceNotFoundException(ResourceNotFoundException error){
        String message = error.getMessage() + " \uD83D\uDE25";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }
}
