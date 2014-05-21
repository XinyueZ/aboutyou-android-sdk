package de.aboutyou.internal.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import de.aboutyou.models.Category;
import de.aboutyou.models.CategoryTree;

public class CategoryTreeResponse extends CollinsResponse<CategoryTree> {

    @SerializedName("category_tree")
    private List<Category> categoryTree;

    @Override
    public CategoryTree get() {
        return new CategoryTree(categoryTree);
    }
}
