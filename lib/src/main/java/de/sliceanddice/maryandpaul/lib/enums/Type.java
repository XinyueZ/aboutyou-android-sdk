package de.sliceanddice.maryandpaul.lib.enums;

public enum Type {

    PRODUCTS("products"),
    CATEGORIES("categories");

    private String name;

    private Type(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
