package de.aboutyou.internal.wrapper;

import java.util.ArrayList;

import de.aboutyou.request.CollinsRequest;

public class RequestEnvelope<T extends CollinsRequest> extends ArrayList<T> {

    public static <T extends CollinsRequest> RequestEnvelope<T> wrap(T request) {
        return new RequestEnvelope<>(request);
    }

    private RequestEnvelope(T request) {
        add(request);
    }

}