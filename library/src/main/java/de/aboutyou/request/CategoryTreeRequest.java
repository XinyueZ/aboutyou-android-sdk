package de.aboutyou.request;

import com.google.gson.annotations.SerializedName;

public class CategoryTreeRequest extends CollinsRequest {

    @SerializedName("category_tree")
    private Object categoryTree = new Object();

}