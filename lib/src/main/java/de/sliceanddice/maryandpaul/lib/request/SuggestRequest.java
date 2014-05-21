package de.sliceanddice.maryandpaul.lib.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import de.sliceanddice.maryandpaul.lib.enums.Type;

public class SuggestRequest extends CollinsRequest {

    private Suggest suggest;

    private static class Suggest {

        private Integer limit;
        private String searchword;
        @SerializedName("categories")
        private List<Long> categoryIds;

    }

    public static class Builder {

        private Integer limit;
        private String searchword;
        private List<Long> categoryIds;

        public Builder(String searchword) {
            this.searchword = searchword;
        }

        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder filterByCategories(List<Long> categoryIds) {
            this.categoryIds = categoryIds;
            return this;
        }

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