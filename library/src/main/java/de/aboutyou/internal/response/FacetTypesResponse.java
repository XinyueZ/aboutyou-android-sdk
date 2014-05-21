package de.aboutyou.internal.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import de.aboutyou.enums.FacetGroup;

public class FacetTypesResponse extends CollinsResponse<List<FacetGroup>> {

    @SerializedName("facet_types")
    private List<Integer> facetTypes;

    @Override
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
