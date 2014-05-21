package de.aboutyou.internal.response;

import java.util.List;

import de.aboutyou.models.Facet;

public class FacetsResponse extends CollinsResponse<List<Facet>> {

    private Facets facets;

    public List<Facet> get() {
        return facets.facet;
    }

    private static class Facets {
        List<Facet> facet;
        Integer hits;
    }
}
