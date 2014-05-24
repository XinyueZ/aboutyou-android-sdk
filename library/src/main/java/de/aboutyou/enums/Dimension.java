package de.aboutyou.enums;

public enum Dimension {

    WIDTH("width"), HEIGHT("height");

    private String searchParam;

    private Dimension(String searchParam) {
        this.searchParam = searchParam;
    }

    public String getSearchParam() {
        return searchParam;
    }
}
