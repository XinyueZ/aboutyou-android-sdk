package de.sliceanddice.maryandpaul.lib.internal.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import de.sliceanddice.maryandpaul.lib.enums.FacetGroup;

public class FacetTypesResponse implements CollinsResponse<List<FacetGroup>> {

    @SerializedName("facet_types")
    private List<Integer> facetTypes;

    public List<FacetGroup> get() {
        List<FacetGroup> facetGroups = new ArrayList<>();
        for (Integer facetGroupId : facetTypes) {
            FacetGroup facetGroup = FacetGroup.fromInteger(facetGroupId);
            if (facetGroup != null) {
                facetGroups.add(facetGroup);
            }
        }
        return facetGroups;
    }

}
