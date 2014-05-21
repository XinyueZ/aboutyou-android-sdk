package de.aboutyou.enums;

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
