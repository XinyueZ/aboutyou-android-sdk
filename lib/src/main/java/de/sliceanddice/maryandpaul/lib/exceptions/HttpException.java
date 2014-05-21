package de.sliceanddice.maryandpaul.lib.exceptions;

import de.sliceanddice.maryandpaul.lib.models.HttpError;

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
