package de.aboutyou.enums;

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
