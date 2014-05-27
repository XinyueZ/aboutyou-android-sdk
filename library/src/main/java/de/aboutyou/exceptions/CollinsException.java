package de.aboutyou.exceptions;

/** Base class for all custom exceptions */
public class CollinsException extends RuntimeException {

    public CollinsException(String message) {
        super(message);
    }

    public CollinsException(Throwable cause) {
        super(cause);
    }
}
