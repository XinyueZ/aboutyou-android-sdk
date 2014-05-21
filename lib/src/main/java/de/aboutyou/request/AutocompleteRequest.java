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

    public static class Builder {

        private Integer limit;
        private String searchword;
        private List<AutocompleteType> types;

        public Builder(String searchword) {
            this.searchword = searchword;
        }

        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder filterByTypes(List<AutocompleteType> types) {
            this.types = types;
            return this;
        }

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