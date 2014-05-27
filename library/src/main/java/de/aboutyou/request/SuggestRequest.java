package de.aboutyou.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SuggestRequest extends CollinsRequest {

    private Suggest suggest;

    private static class Suggest {

        private Integer limit;
        private String searchword;
        @SerializedName("categories")
        private List<Long> categoryIds;

    }

    public static class Builder extends CollinsRequest.Builder<SuggestRequest> {

        private Integer limit;
        private String searchword;
        private List<Long> categoryIds;

        /**
         * Constructs a new Builder for a {@link de.aboutyou.request.SuggestRequest}
         * @param searchword The searchword used for suggestions, not null or empty
         */
        public Builder(String searchword) {
            validateNotEmpty(searchword, "searchword");
            this.searchword = searchword;
        }

        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        /** Filter resulst by category ids  */
        public Builder filterByCategories(List<Long> categoryIds) {
            this.categoryIds = categoryIds;
            return this;
        }

        /** {@inheritDoc} */
        public SuggestRequest build() {
            SuggestRequest autocompleteRequest = new SuggestRequest();

            Suggest suggest = new Suggest();
            suggest.searchword = searchword;
            suggest.limit = limit;
            suggest.categoryIds = categoryIds;
            autocompleteRequest.suggest = suggest;

            return autocompleteRequest;
        }
    }

}