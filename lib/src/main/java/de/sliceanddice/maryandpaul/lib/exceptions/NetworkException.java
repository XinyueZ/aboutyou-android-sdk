package de.sliceanddice.maryandpaul.lib.exceptions;

public class NetworkException extends CollinsException {

    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(Throwable cause) {
        super(cause);
    }
}
