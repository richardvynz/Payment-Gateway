package com.example.gateway.exceptions;

public class ValidationException extends RuntimeException{
    public ValidationException(String msg) {
        super(msg);
    }
}
