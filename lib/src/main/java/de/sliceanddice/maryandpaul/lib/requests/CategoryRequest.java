package de.sliceanddice.maryandpaul.lib.requests;

import java.util.List;

public class CategoryRequest extends BaseRequest {

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

        public CategoryRequest build() {
            CategoryRequest categoryRequest = new CategoryRequest();

            Category category = new Category();
            category.ids = ids;
            categoryRequest.category = category;

            return categoryRequest;
        }
    }

}