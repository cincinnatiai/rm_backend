package com.example.demo.exceptions;

import lombok.Getter;

@Getter
public enum ErrorMessage {
    USE_VALID_ID("Use a valid Id"),
    USER_NOT_FOUND("User not found"),
    USE_VALID_PROPERTIES("Use valid properties"),
    INTERNAL_SERVER_ERROR("Internal server error");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }
}