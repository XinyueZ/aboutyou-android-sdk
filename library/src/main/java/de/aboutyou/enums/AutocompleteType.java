package de.aboutyou.enums;

/** Autocomplete type selection */
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
