package de.sliceanddice.maryandpaul.lib.request;

import java.util.List;

public class CategoriesRequest implements CollinsRequest {

    private Category category;

    private static class Category {

        private List<Long> ids;
    }

    public static class Builder {

        private List<Long> ids;

        public Builder filterByCategoryIds(List<Long> categoryIds) {
            this.ids = categoryIds;
            return this;
        }

        public CategoriesRequest build() {
            CategoriesRequest categoriesRequest = new CategoriesRequest();

            Category category = new Category();
            category.ids = ids;
            categoriesRequest.category = category;

            return categoriesRequest;
        }
    }

}