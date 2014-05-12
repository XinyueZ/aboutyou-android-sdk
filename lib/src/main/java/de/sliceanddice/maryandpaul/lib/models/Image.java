package de.sliceanddice.maryandpaul.lib.models;

import com.google.gson.annotations.SerializedName;

public class Image {

    private static final String CDN_URL = "http://cdn.mary-paul.de/file/"; // TODO mode to proper location

    private String mime;
    @SerializedName("image")
    private Dimensions dimensions;
    private String hash;
    private String ext;
    private long size;

    public String getUrl() {
        return String.format("%s%s", CDN_URL, hash);
    }

    public String getUrlWithWidth(int width) {
        return String.format("%s%s%d", getUrl(), "?width=", width);
    }

    public String getUrlWithHeight(int height) {
        return String.format("%s%s%d", getUrl(), "?height=", height);
    }

    public String getMime() {
        return mime;
    }

    public void setMime(String mime) {
        this.mime = mime;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    private class Dimensions {
        int width;
        int height;
    }
}
