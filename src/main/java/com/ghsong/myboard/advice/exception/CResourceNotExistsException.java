package com.ghsong.myboard.advice.exception;

public class CResourceNotExistsException extends RuntimeException {
    public CResourceNotExistsException() {
    }

    public CResourceNotExistsException(String message) {
        super(message);
    }

    public CResourceNotExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
