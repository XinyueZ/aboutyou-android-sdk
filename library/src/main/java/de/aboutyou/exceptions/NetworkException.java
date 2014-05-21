package de.aboutyou.exceptions;

public class NetworkException extends CollinsException {

    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(Throwable cause) {
        super(cause);
    }
}
