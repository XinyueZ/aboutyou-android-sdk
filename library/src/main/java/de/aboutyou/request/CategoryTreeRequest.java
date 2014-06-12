package de.aboutyou.request;

import com.google.gson.annotations.SerializedName;

public class CategoryTreeRequest extends CollinsRequest {

    @SerializedName("category_tree")
    private Object categoryTree;

    private static class CategoryTree {
        @SerializedName("max_depth")
        private Integer maxDepth;
    }

    public static class Builder extends CollinsRequest.Builder<CategoryTreeRequest> {

        private int maxDepth = DEFAULT_INT;

        /** Limit tree to a specific depth */
        public Builder setMaxDepth(int maxDepth) {
            if (maxDepth < 0 || maxDepth > 10) {
                throw new IllegalArgumentException("maxDepth has to be between 0 and 10");
            }
            this.maxDepth = maxDepth;
            return this;
        }

        @Override
        public CategoryTreeRequest build() {
            CategoryTreeRequest categoryTreeRequest = new CategoryTreeRequest();

            CategoryTree categoryTree = new CategoryTree();

            if (maxDepth != DEFAULT_INT) {
                categoryTree.maxDepth = maxDepth;
            }

            categoryTreeRequest.categoryTree = categoryTree;

            return categoryTreeRequest;
        }
    }

}