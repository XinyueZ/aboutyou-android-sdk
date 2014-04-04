package de.sliceanddice.maryandpaul.lib.request;

import com.google.gson.annotations.SerializedName;

public class FacetTypesRequest implements CollinsRequest {

    @SerializedName("facet_types")
    private Object facetTypes = new Object();


}