package de.sliceanddice.maryandpaul.lib.internal.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import de.sliceanddice.maryandpaul.lib.models.Category;
import de.sliceanddice.maryandpaul.lib.models.CategoryTree;

public class CategoryTreeResponse extends CollinsResponse<CategoryTree> {

    @SerializedName("category_tree")
    private List<Category> categoryTree;

    public CategoryTree get() {
        return new CategoryTree(categoryTree);
    }
}
