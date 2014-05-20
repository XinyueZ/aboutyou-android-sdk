package de.sliceanddice.maryandpaul.lib.enums;

public enum SimpleColor {

    BLUE(1),
    BLACK(11),
    GREY(12),
    ORANGE(14),
    BROWN(15),
    RED(18),
    LILA(30),
    BEIGE(48),
    COLOR(55),
    YELLOW(67),
    SILVER(168),
    PINK(204),
    GOLD(247),
    WHITE(570),
    GREEN(579);

    private int facetId;

    private SimpleColor(int facetId){
        this.facetId = facetId;
    }

    public static SimpleColor forFacetId(int facetId) {
        for (SimpleColor color : SimpleColor.values()) {
            if (color.getFacetId() == facetId) {
                return color;
            }
        }
        throw new IllegalArgumentException(String.format("FacetId %d is not valid!", facetId));
    }

    public int getFacetId() {
        return facetId;
    }
}
