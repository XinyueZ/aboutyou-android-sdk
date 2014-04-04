package de.sliceanddice.maryandpaul.lib.internal.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.sliceanddice.maryandpaul.lib.models.Category;

public class CategoriesResponse implements CollinsResponse<List<Category>> {

    @SerializedName("category")
    private Map<Long, Category> categories;

    public List<Category> get() {
        return new ArrayList<>(categories.values());
    }
}
