package de.sliceanddice.maryandpaul.lib.wrapper;

import de.sliceanddice.maryandpaul.lib.util.ImmutableList;
import de.sliceanddice.maryandpaul.lib.wrapper.request.BaseWrapper;

public class ResponseWrapper<T extends BaseWrapper> extends ImmutableList<T> {

    public T getWrappedObject() {
        return get(0);
    }

}
