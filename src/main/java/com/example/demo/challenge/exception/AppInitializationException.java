package com.example.demo.challenge.exception;

public class AppInitializationException extends RuntimeException {

    public AppInitializationException(String message) {
        super(message);
    }

    public AppInitializationException(String message, Throwable cause) {
        super(message, cause);
    }

}
