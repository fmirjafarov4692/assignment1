package com.example.model.exception;

public class NotFoundException extends RuntimeException {
    private String code;

    public NotFoundException(String message, String code) {
        super(message);
        this.code = code;
    }
}