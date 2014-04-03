package de.sliceanddice.maryandpaul.lib.wrapper.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FacetTypesWrapper extends BaseWrapper {

    @SerializedName("facet_types")
    private List<Integer> facetTypes;

    public List<Integer> getFacetTypes() {
        return facetTypes;
    }

    public void setFacetTypes(List<Integer> facetTypes) {
        this.facetTypes = facetTypes;
    }
}
