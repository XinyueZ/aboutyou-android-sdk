package de.aboutyou.internal.wrapper;

import java.util.ArrayList;

import de.aboutyou.internal.response.CollinsResponse;

public class ResponseEnvelope<T extends CollinsResponse> extends ArrayList<T> {

    public T unwrap() {
        return get(0);
    }

}
