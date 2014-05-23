package de.aboutyou.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Product extends FallibleModel {

    private String name;
    @SerializedName("description_long")
    private String descriptionLong;
    private boolean sale;
    @SerializedName("max_price")
    private long maxPrice;
    @SerializedName("brand_id")
    private long brandId;
    @SerializedName("min_price")
    private long minPrice;
    @SerializedName("description_short")
    private String descriptionShort;
    private long id;
    private List<Variant> variants;
    @SerializedName("default_image")
    private Image image;
    private List<Product> styles;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescriptionLong() {
        return descriptionLong;
    }

    public void setDescriptionLong(String descriptionLong) {
        this.descriptionLong = descriptionLong;
    }

    public boolean getSale() {
        return sale;
    }

    public void setSale(boolean sale) {
        this.sale = sale;
    }

    public long getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(long maxPrice) {
        this.maxPrice = maxPrice;
    }

    public long getBrandId() {
        return brandId;
    }

    public void setBrandId(long brandId) {
        this.brandId = brandId;
    }

    public Long getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(long minPrice) {
        this.minPrice = minPrice;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Variant> getVariants() {
        return variants;
    }

    public void setVariants(List<Variant> variants) {
        this.variants = variants;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Product> getStyles() {
        return styles;
    }

    public void setStyles(List<Product> styles) {
        this.styles = styles;
    }
}
