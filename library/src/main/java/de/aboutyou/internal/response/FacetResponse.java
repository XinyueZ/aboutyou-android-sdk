package de.aboutyou.internal.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import de.aboutyou.models.Facet;

public class FacetResponse extends CollinsResponse<List<Facet>> {

    @SerializedName("facet")
    private List<Facet> facets;

    @Override
    public List<Facet> get() {
        return facets;
    }

}
