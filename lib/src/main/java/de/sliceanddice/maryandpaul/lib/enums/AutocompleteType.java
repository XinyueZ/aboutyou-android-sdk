package de.sliceanddice.maryandpaul.lib.enums;

public enum AutocompleteType {

    PRODUCTS("products"),
    CATEGORIES("categories");

    private String name;

    private AutocompleteType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
