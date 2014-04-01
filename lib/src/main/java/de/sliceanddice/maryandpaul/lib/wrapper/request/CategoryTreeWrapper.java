package de.sliceanddice.maryandpaul.lib.wrapper.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import de.sliceanddice.maryandpaul.lib.models.Category;

public class CategoryTreeWrapper extends BaseWrapper {

    @SerializedName("category_tree")
    private List<Category> categoryTree;

    public List<Category> getCategoryTree() {
        return categoryTree;
    }
}
