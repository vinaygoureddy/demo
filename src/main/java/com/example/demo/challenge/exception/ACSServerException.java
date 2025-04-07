package com.example.demo.challenge.exception;

public abstract class ACSServerException extends RuntimeException {

    public ACSServerException(String message) {
        super(message);
    }

    public ACSServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
