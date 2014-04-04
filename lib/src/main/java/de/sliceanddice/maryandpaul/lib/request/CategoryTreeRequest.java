package de.sliceanddice.maryandpaul.lib.request;

import com.google.gson.annotations.SerializedName;

public class CategoryTreeRequest implements CollinsRequest {

    @SerializedName("category_tree")
    private Object categoryTree = new Object();

}