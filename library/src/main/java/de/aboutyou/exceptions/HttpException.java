package de.aboutyou.exceptions;

import de.aboutyou.models.HttpError;

/** An exception set off by the API backend */
public class HttpException extends CollinsException {

    private HttpError httpError;

    public HttpException(HttpError httpError, String message) {
        super(message);
        this.httpError = httpError;
    }

    public HttpError getHttpError() {
        return httpError;
    }

}
