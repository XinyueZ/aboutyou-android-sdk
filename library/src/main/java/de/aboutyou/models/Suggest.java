package de.aboutyou.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Suggest extends BaseModel implements List<String> {

    private List<String> delegate;

    public Suggest() {
        delegate = new ArrayList<>();
    }

    @Override
    public void add(int location, String object) {
        delegate.add(location, object);
    }

    @Override
    public boolean add(String object) {
        return delegate.add(object);
    }

    @Override
    public boolean addAll(int location, Collection<? extends String> collection) {
        return delegate.addAll(location, collection);
    }

    @Override
    public boolean addAll(Collection<? extends String> collection) {
        return delegate.addAll(collection);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public boolean contains(Object object) {
        return delegate.contains(object);
    }

    @Override
    public boolean containsAll(Collection<?> collection) {
        return delegate.containsAll(collection);
    }

    @Override
    public String get(int location) {
        return delegate.get(location);
    }

    @Override
    public int indexOf(Object object) {
        return delegate.indexOf(object);
    }

    @Override
    public boolean isEmpty() {
        return delegate.isEmpty();
    }

    @Override
    public Iterator<String> iterator() {
        return delegate.iterator();
    }

    @Override
    public int lastIndexOf(Object object) {
        return delegate.lastIndexOf(object);
    }

    @Override
    public ListIterator<String> listIterator() {
        return delegate.listIterator();
    }

    @Override
    public ListIterator<String> listIterator(int location) {
        return delegate.listIterator(location);
    }

    @Override
    public String remove(int location) {
        return delegate.remove(location);
    }

    @Override
    public boolean remove(Object object) {
        return delegate.remove(object);
    }

    @Override
    public boolean removeAll(Collection<?> collection) {
        return delegate.removeAll(collection);
    }

    @Override
    public boolean retainAll(Collection<?> collection) {
        return delegate.retainAll(collection);
    }

    @Override
    public String set(int location, String object) {
        return delegate.set(location, object);
    }

    @Override
    public int size() {
        return delegate.size();
    }

    @Override
    public List<String> subList(int start, int end) {
        return delegate.subList(start, end);
    }

    @Override
    public Object[] toArray() {
        return delegate.toArray();
    }

    @Override
    public <T> T[] toArray(T[] array) {
        return delegate.toArray(array);
    }
}
