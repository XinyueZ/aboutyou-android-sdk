package de.aboutyou.request;

import java.util.List;
import java.util.Map;

public abstract class CollinsRequest {

    protected abstract static class Builder<T> {

        protected int DEFAULT_INT = -1;
        protected int DEFAULT_LONG = -1;

        protected void validateNotEmpty(String arg, String argDesc) {
            if (arg == null || arg.isEmpty()) {
                throw new IllegalArgumentException(String.format("%s must not be NULL or empty", argDesc));
            }
        }

        protected void validateNotEmpty(List list, String argDesc) {
            if (list == null || list.isEmpty()) {
                throw new IllegalArgumentException(String.format("%s must not be NULL or empty", argDesc));
            }
        }

        protected void validateNotEmpty(Map map, String argDesc) {
            if (map == null || map.isEmpty()) {
                throw new IllegalArgumentException(String.format("%s must not be NULL or empty", argDesc));
            }
        }

        protected void validateSet(long arg, String argDesc) {
            if (arg == DEFAULT_LONG) {
                throw new IllegalArgumentException(String.format("%s has to be set", argDesc));
            }
        }

        protected void validateSet(int arg, String argDesc) {
            if (arg == DEFAULT_INT) {
                throw new IllegalArgumentException(String.format("%s has to be set", argDesc));
            }
        }

        /** Builds the request, throws an exception if invalid data was provided */
        public abstract T build();
    }
}
