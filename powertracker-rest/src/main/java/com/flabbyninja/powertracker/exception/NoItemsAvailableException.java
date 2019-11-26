package com.flabbyninja.powertracker.exception;

public class NoItemsAvailableException extends Exception {
    public NoItemsAvailableException() {
    }

    public NoItemsAvailableException(String message) {
        super(message);
    }

    public NoItemsAvailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoItemsAvailableException(Throwable cause) {
        super(cause);
    }

    public NoItemsAvailableException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
