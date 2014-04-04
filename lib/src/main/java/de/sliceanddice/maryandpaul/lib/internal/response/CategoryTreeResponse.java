package de.sliceanddice.maryandpaul.lib.internal.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import de.sliceanddice.maryandpaul.lib.models.Category;

public class CategoryTreeResponse implements CollinsResponse<List<Category>> {

    @SerializedName("category_tree")
    private List<Category> categoryTree;

    public List<Category> get() {
        return categoryTree;
    }
}
