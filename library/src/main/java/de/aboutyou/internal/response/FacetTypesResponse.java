package de.aboutyou.internal.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import de.aboutyou.enums.FacetType;

public class FacetTypesResponse extends CollinsResponse<List<FacetType>> {

    @SerializedName("facet_types")
    private List<Integer> facetTypes;

    @Override
    public List<FacetType> get() {
        List<FacetType> facetTypes = new ArrayList<>();
        for (Integer facetGroupId : this.facetTypes) {
            FacetType facetType = FacetType.fromInteger(facetGroupId);
            if (facetType != null) {
                facetTypes.add(facetType);
            }
        }
        return facetTypes;
    }

}
