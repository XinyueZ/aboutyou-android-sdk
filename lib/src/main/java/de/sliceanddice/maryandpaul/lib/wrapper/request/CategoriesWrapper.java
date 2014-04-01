package de.sliceanddice.maryandpaul.lib.wrapper.request;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.sliceanddice.maryandpaul.lib.models.Category;

public class CategoriesWrapper extends BaseWrapper {

    @SerializedName("category")
    private Map<Long, Category> categories;

    public List<Category> getCategories() {
        return new ArrayList<>(categories.values());
    }
}
