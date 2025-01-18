package com.tanvir.dems.exceptions;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
    public ApiException(){
    }
}
