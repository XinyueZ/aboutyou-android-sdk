package de.aboutyou.enums;

/** Filter for product sale state */
public enum ProductFilter {

    SALEONLY(true),
    NONSALEONLY(false),
    ALL(null);

    private Boolean value;

    private ProductFilter(Boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}
