package com.geodesy.web.geodesy.service.exceptions;

public class CheckFailedException extends RuntimeException {
    public CheckFailedException(String message) {
        super(message);
    }

    public CheckFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
