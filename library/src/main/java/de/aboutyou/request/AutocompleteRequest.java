package de.aboutyou.request;

import java.util.List;

import de.aboutyou.enums.AutocompleteType;

public class AutocompleteRequest extends CollinsRequest {

    private Autocompletion autocompletion;

    private static class Autocompletion {

        private Integer limit;
        private String searchword;
        private List<AutocompleteType> types;

    }

    public static class Builder extends CollinsRequest.Builder<AutocompleteRequest> {

        private Integer limit;
        private String searchword;
        private List<AutocompleteType> types;

        /**
         * Constructs a new Builder for an {@link de.aboutyou.request.AutocompleteRequest}
         * @param searchword The searchword used for autocompletion, not null or empty
         */
        public Builder(String searchword) {
            validateNotEmpty(searchword, "searchword");
            this.searchword = searchword;
        }

        /** Filter results by object type */
        public Builder filterByTypes(List<AutocompleteType> types) {
            this.types = types;
            return this;
        }

        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        /** {@inheritDoc} */
        public AutocompleteRequest build() {
            AutocompleteRequest autocompleteRequest = new AutocompleteRequest();

            Autocompletion autocompletion = new Autocompletion();
            autocompletion.searchword = searchword;
            autocompletion.limit = limit;
            autocompletion.types = types;
            autocompleteRequest.autocompletion = autocompletion;

            return autocompleteRequest;
        }
    }

}