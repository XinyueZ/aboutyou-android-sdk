package de.aboutyou.models;

import com.google.gson.annotations.SerializedName;

import android.annotation.SuppressLint;

import de.aboutyou.ShopApiClient;
import de.aboutyou.enums.Dimension;

@SuppressLint("DefaultLocale")
public class Image {

    private String mime;
    @SerializedName("image")
    private Dimensions dimensions;
    private String hash;
    @SerializedName("ext")
    private String extension;
    private long size;

    public String getUrl() {
        return String.format("%s%s", ShopApiClient.CDN_URL, hash);
    }

    public String getUrl(Dimension dimension, int size) {
        return String.format("%s?%s=%d", getUrl(), dimension.getSearchParam(), size);
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

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public class Dimensions {
        private int width;
        private int height;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
