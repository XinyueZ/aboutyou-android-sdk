package de.sliceanddice.maryandpaul.lib.requests;

import com.google.gson.annotations.SerializedName;

public class CategoryTreeRequest extends BaseRequest {

    @SerializedName("category_tree")
    private Object categoryTree = new Object();

}