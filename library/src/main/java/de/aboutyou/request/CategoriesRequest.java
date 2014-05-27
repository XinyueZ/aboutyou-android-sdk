package de.aboutyou.request;

import java.util.List;

public class CategoriesRequest extends CollinsRequest {

    private Category category;

    private static class Category {

        private List<Long> ids;
    }

    public static class Builder extends CollinsRequest.Builder<CategoriesRequest> {

        private List<Long> ids;

        /** Filter results by category ids */
        public Builder filterByCategoryIds(List<Long> categoryIds) {
            this.ids = categoryIds;
            return this;
        }

        /** {@inheritDoc} */
        public CategoriesRequest build() {
            CategoriesRequest categoriesRequest = new CategoriesRequest();

            Category category = new Category();
            category.ids = ids;
            categoriesRequest.category = category;

            return categoriesRequest;
        }
    }

}