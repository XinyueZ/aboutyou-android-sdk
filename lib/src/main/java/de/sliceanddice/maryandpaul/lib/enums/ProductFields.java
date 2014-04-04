package de.sliceanddice.maryandpaul.lib.enums;

public enum ProductFields {

    ID("id"),
    NAME("name"),
    ACTIVE("active"),
    BRAND_ID("brand_id"),
    DESCRIPTION_SHORT("description_long"),
    DESCRIPTION_LONG("description_short"),
    DEFAULT_VARIANT("default_variant"),
    VARIANTS("variants"),
    MIN_PRICE("min_price"),
    MAX_PRICE("max_price"),
    SALE("sale"),
    DEFAULT_IMAGE("default_image"),
    ATTRIBUTES_MERGE("attributes_merged"),
    CATEGORIES("categories"),
    MAX_SAVINGS("max_savings"),
    MAX_SAVINGS_PERCENTAGE("max_savings_percentage"),
    TAGS("tags");

    String name;

    private ProductFields(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
