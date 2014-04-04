package de.sliceanddice.maryandpaul.lib.wrapper;

import de.sliceanddice.maryandpaul.lib.requests.BaseRequest;
import de.sliceanddice.maryandpaul.lib.util.ImmutableList;

public class RequestWrapper<T extends BaseRequest> extends ImmutableList<T> {

    public static <T extends BaseRequest> RequestWrapper<T> wrap(T request) {
        return new RequestWrapper<>(request);
    }

    private RequestWrapper(T request) {
        add(request);
    }

}