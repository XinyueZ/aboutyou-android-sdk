package de.sliceanddice.maryandpaul.lib.wrapper;

import java.util.ArrayList;

import de.sliceanddice.maryandpaul.lib.wrapper.request.BaseWrapper;

public class ResponseWrapper<T extends BaseWrapper> extends ArrayList<T> {

    public T getWrappedObject() {
        return get(0);
    }

}
