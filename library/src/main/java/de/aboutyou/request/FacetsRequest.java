package de.aboutyou.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import de.aboutyou.enums.FacetType;

public class FacetsRequest extends CollinsRequest {

    private Facet facets = new Facet();

    private static class Facet {
        @SerializedName("group_ids")
        private List<FacetType> facetTypes;
        private Integer limit;
        private Integer offset;
    }

    public static class Builder extends CollinsRequest.Builder {

        private List<FacetType> facetTypes;
        private Integer limit;
        private Integer offset;

        public Builder filterByFacetGroup(List<FacetType> facetTypes) {
            this.facetTypes = facetTypes;
            return this;
        }

        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder offset(Integer offset) {
            this.offset = offset;
            return this;
        }

        public FacetsRequest build() {
            FacetsRequest facetRequest = new FacetsRequest();

            Facet facet = new Facet();
            facet.facetTypes = facetTypes;
            facet.limit = limit;
            facet.offset = offset;
            facetRequest.facets = facet;

            return facetRequest;
        }

    }
}