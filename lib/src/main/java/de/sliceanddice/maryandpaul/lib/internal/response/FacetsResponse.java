package de.sliceanddice.maryandpaul.lib.internal.response;

import java.util.List;

import de.sliceanddice.maryandpaul.lib.models.Facet;

public class FacetsResponse implements CollinsResponse<List<Facet>> {

    private Facets facets;

    public List<Facet> get() {
        return facets.facet;
    }

    private static class Facets {
        List<Facet> facet;
        Integer hits;
    }
}
