package de.aboutyou.request;

import java.util.List;

public abstract class CollinsRequest {

    protected abstract static class Builder<T> {

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

        /** Builds the request, throws an exception if invalid data was provided */
        public abstract T build();
    }
}
