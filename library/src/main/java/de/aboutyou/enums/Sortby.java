package de.aboutyou.enums;

/** Sort column for product search*/
public enum Sortby {

    RELEVANCE("relevance"),
    DATE_UPDATED("updated_date"),
    DATE_CREATED("created_date"),
    MOST_VIEWED("most_viewed"),
    PRICE("price");

    private String name;

    private Sortby(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
