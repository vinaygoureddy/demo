package com.example.demo.challenge.exception;

import com.example.demo.challenge.enums.ErrorCode;
import com.example.demo.challenge.enums.MessageType;

public class InvalidDataElementException extends ACSServerException {

    private final MessageType messageType;
    private final String fieldName;

    public InvalidDataElementException(MessageType messageType, String fieldName) {
        super(ErrorCode.INVALID_DATA_ELEMENT.getErrorCodeText());
        this.messageType = messageType;
        this.fieldName = fieldName;
    }
}
