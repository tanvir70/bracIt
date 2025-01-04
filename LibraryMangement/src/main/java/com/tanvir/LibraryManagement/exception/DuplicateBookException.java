package com.tanvir.LibraryManagement.exception;

public class DuplicateBookException extends RuntimeException {
    public DuplicateBookException(String message) {
        super(message);
    }

    public DuplicateBookException() {
    }
}
