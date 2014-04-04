package de.sliceanddice.maryandpaul.lib.internal.wrapper;

import java.util.ArrayList;

import de.sliceanddice.maryandpaul.lib.request.CollinsRequest;

public class RequestEnvelope<T extends CollinsRequest> extends ArrayList<T> {

    public static <T extends CollinsRequest> RequestEnvelope<T> wrap(T request) {
        return new RequestEnvelope<>(request);
    }

    private RequestEnvelope(T request) {
        add(request);
    }

}