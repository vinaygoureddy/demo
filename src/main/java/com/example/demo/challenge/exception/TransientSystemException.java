package com.example.demo.challenge.exception;


import com.example.demo.challenge.enums.MessageType;

public class TransientSystemException extends ACSServerException {

    private MessageType messageType;

    public TransientSystemException(String message) {
        super(message);
    }

    public TransientSystemException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransientSystemException(String message, Throwable cause, MessageType messageType) {
        super(message, cause);
        this.messageType = messageType;
    }
}
