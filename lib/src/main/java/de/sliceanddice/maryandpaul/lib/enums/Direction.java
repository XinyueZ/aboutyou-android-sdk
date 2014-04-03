package de.sliceanddice.maryandpaul.lib.enums;

public enum Direction {

    ASC("asc"),
    DESC("desc");

    private String name;

    private Direction(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
