package com.example.jpa.user.exception;

public class ExistEmailException extends RuntimeException {
    public ExistEmailException(String message) {
        super(message);
    }
}
