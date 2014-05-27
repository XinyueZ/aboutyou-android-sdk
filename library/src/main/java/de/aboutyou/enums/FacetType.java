package de.aboutyou.enums;

public enum FacetType {

    BRAND(0),
    COLOR(1),
    SIZE(2),
    GENDERAGE(3),
    CUPSIZE(4),
    LENGTH(5),
    DIMENSION(6),
    SIZE_RUN(172),
    CLOTHING_UNISEX_INT(173),
    CLOTHING_UNISEX_INCH(174),
    CLOTHING_WOMEN_DE(175),
    CLOTHING_WOMEN_INCH(180),
    CLOTHING_WOMEN_BELTS_CM(181),
    CLOTHING_MEN_DE(187),
    CLOTHING_MEN_INCH(189),
    CLOTHING_MEN_BELTS_CM(190),
    SHOES_UNISEX_EUR(194),
    SHOES_UNISEX_ADIDAS_EUR(195),
    CLOTHING_UNISEX_ONESIZE(204),
    SIZE_CODE(206),
    CHANNEL(211),
    CLOTHING_HATS_US(231),
    CARE_SYMBOL(247);

    private int id;

    private FacetType(int id) {
        this.id = id;
    }

    public static FacetType fromInteger(int index) {
        for (FacetType currentValue : FacetType.values()) {
            if (currentValue.getId() == index) {
                return currentValue;
            }
        }
        return null;
    }

    public int getId() {
        return id;
    }

}
