package de.aboutyou.exceptions;

public class CollinsException extends RuntimeException {

    public CollinsException(String message) {
        super(message);
    }

    public CollinsException(Throwable cause) {
        super(cause);
    }
}
