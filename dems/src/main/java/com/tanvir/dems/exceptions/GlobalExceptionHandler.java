package com.tanvir.dems.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> myResourceNotFoundException(ResourceNotFoundException error){
        String message = error.getMessage()+ " \uD83D\uDE25";
        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<String> myAPIException(ApiException error){
        String message = error.getMessage() + " &#128517";
        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<String> myResourceAlreadyExistsException(ResourceAlreadyExistsException error){
        String message = error.getMessage();
        return new ResponseEntity<>(message,HttpStatus.CONFLICT);
    }
}
