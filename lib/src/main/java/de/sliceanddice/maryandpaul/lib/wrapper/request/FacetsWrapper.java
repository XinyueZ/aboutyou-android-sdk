package de.sliceanddice.maryandpaul.lib.wrapper.request;

import java.util.List;

import de.sliceanddice.maryandpaul.lib.models.Facet;

public class FacetsWrapper extends BaseWrapper {

    private Facets facets;

    public List<Facet> getFacets() {
        return facets.facet;
    }

    private static class Facets {
        List<Facet> facet;
        Integer hits;
    }
}
