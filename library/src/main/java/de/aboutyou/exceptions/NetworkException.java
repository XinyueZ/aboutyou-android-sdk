package de.aboutyou.exceptions;

/** An exception on network level */
public class NetworkException extends CollinsException {

    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(Throwable cause) {
        super(cause);
    }
}
