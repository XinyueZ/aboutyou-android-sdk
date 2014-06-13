package de.aboutyou.request;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.aboutyou.enums.FacetType;

public class FacetRequest extends CollinsRequest {

    @SerializedName("facet")
    private List<Facet> facets = new ArrayList<>();

    private static class Facet {
        private Long id;
        @SerializedName("group_id")
        private FacetType groupId;
    }

    public static class Builder extends CollinsRequest.Builder<FacetRequest> {

        private Map<Long, FacetType> facetMap;

        /** Select facets */
        public Builder selectFacets(Map<Long, FacetType> facetMap) {
            validateNotEmpty(facetMap, "facetMap");
            this.facetMap = facetMap;
            return this;
        }

        /** {@inheritDoc} */
        public FacetRequest build() {
            validateNotEmpty(facetMap, "facetMap");
            FacetRequest facetRequest = new FacetRequest();

            List<Facet> facets = new ArrayList<>();
            for (Map.Entry<Long, FacetType> entry : facetMap.entrySet()) {
                Facet facet = new Facet();
                facet.id = entry.getKey();
                facet.groupId = entry.getValue();
                facets.add(facet);
            }
            facetRequest.facets = facets;

            return facetRequest;
        }

    }
}