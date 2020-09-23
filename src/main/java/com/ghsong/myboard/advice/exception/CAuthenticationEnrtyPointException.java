package com.ghsong.myboard.advice.exception;

public class CAuthenticationEnrtyPointException extends RuntimeException {

    public CAuthenticationEnrtyPointException() {
    }

    public CAuthenticationEnrtyPointException(String message) {
        super(message);
    }

    public CAuthenticationEnrtyPointException(String message, Throwable cause) {
        super(message, cause);
    }
}
