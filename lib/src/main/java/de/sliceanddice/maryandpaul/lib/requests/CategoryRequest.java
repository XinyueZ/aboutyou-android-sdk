package de.sliceanddice.maryandpaul.lib.requests;

import java.util.List;

public class CategoryRequest extends BaseRequest {

    private Category category = new Category();

    public void setCategoryIds(List<Long> categoryIds) {
        category.ids = categoryIds;
    }

    private static class Category {

        List<Long> ids;
    }

}