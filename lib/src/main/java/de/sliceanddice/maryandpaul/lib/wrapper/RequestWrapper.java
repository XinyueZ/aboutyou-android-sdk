package de.sliceanddice.maryandpaul.lib.wrapper;

import java.util.ArrayList;

import de.sliceanddice.maryandpaul.lib.requests.BaseRequest;

public class RequestWrapper<T extends BaseRequest> extends ArrayList<T> {

    public static <T extends BaseRequest> RequestWrapper<T> wrap(T request) {
        return new RequestWrapper<>(request);
    }

    private RequestWrapper(T request) {
        add(request);
    }

}