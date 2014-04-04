package de.sliceanddice.maryandpaul.lib.requests;

import com.google.gson.annotations.SerializedName;

public class FacetTypesRequest extends BaseRequest {

    @SerializedName("facet_types")
    private Object facetTypes = new Object();


}