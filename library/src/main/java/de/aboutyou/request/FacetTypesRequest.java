package de.aboutyou.request;

import com.google.gson.annotations.SerializedName;

public class FacetTypesRequest extends CollinsRequest {

    @SerializedName("facet_types")
    private Object facetTypes = new Object();


}