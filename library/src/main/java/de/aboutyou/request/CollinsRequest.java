package de.aboutyou.request;

public abstract class CollinsRequest {

    protected static class Builder {

        protected void validateNotEmpty(String arg, String argDesc) {
            if (arg == null || arg.isEmpty()) {
                throw new IllegalArgumentException(String.format("%s must not be NULL or empty", argDesc));
            }
        }
    }
}
