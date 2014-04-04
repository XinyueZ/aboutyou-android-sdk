package de.sliceanddice.maryandpaul.lib.internal.wrapper;

import java.util.ArrayList;

import de.sliceanddice.maryandpaul.lib.internal.response.CollinsResponse;

public class ResponseEnvelope<T extends CollinsResponse> extends ArrayList<T> {

    public T unwrap() {
        return get(0);
    }

}
