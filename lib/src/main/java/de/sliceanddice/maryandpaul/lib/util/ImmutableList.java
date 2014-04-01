package de.sliceanddice.maryandpaul.lib.util;

import java.util.ArrayList;
import java.util.Collection;

// TODO proper implementation for immutable single element list
public class ImmutableList<T> extends ArrayList<T> {

    @Override
    public boolean add(T object) {
        if (size() > 0) {
            throw new IllegalStateException();
        }
        return super.add(object);
    }

    @Override
    public void add(int index, T object) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> collection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object object) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void removeRange(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T set(int index, T object) {
        throw new UnsupportedOperationException();
    }

}
