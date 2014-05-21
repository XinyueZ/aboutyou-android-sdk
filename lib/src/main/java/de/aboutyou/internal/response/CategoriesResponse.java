package de.aboutyou.internal.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.aboutyou.models.Category;

public class CategoriesResponse extends CollinsResponse<List<Category>> {

    @SerializedName("category")
    private Map<Long, Category> categories;

    public List<Category> get() {
        return new ArrayList<>(categories.values());
    }
}
