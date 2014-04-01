package de.sliceanddice.maryandpaul.lib.requests;

import java.util.List;

import de.sliceanddice.maryandpaul.lib.enums.FacetGroup;

public class FacetRequest extends BaseRequest {

    private Facet facets = new Facet();

    public void setGroup_ids(List<FacetGroup> group_ids) {
        facets.group_ids = group_ids;
    }

    public void setLimit(Integer limit) {
        facets.limit = limit;
    }

    public void setOffset(Integer offset) {
        facets.offset = offset;
    }

    private static class Facet {
        List<FacetGroup> group_ids;
        Integer limit;
        Integer offset;
    }
}