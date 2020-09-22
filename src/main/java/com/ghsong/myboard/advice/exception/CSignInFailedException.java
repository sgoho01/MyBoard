package com.ghsong.myboard.advice.exception;

public class CSignInFailedException extends RuntimeException {

    public CSignInFailedException() {
        super();
    }

    public CSignInFailedException(String message) {
        super(message);
    }

    public CSignInFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
