package de.aboutyou.models;

import java.util.HashMap;
import java.util.List;

import de.aboutyou.enums.FacetType;

public class Attributes extends HashMap<Integer, List<Long>> {

    public List<Long> get(FacetType facetType) {
        if (contains(facetType)){
            return get(facetType.getId());
        } else {
            return null;
        }
    }

    public boolean contains(FacetType facetType) {
        return containsKey(facetType.getId());
    }

}
