package com.petit.toon.exception;

public abstract class PetitToonException extends RuntimeException {

    public PetitToonException(String message) {
        super(message);
    }

    public PetitToonException(String message, Throwable cause) {
        super(message, cause);
    }

    public abstract int getStatusCode();
}
