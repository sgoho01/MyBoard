package com.ghsong.myboard.advice.exception;

public class CForbiddenWorkException extends RuntimeException {
    public CForbiddenWorkException() {
    }

    public CForbiddenWorkException(String message) {
        super(message);
    }

    public CForbiddenWorkException(String message, Throwable cause) {
        super(message, cause);
    }
}
