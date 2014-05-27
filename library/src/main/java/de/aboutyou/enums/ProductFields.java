package de.aboutyou.enums;

/** Product fields to be request as part of a product search */
public enum ProductFields {

    ID("id"),
    NAME("name"),
    ACTIVE("active"),
    BRAND_ID("brand_id"),
    DESCRIPTION_SHORT("description_short"),
    DESCRIPTION_LONG("description_long"),
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
