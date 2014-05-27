package de.aboutyou.enums;

/** Sort direction for product search */
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
