package de.sliceanddice.maryandpaul.lib.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Variant {

    @SerializedName("updated_date")
    private Date updatedDate;
    private String ean;
    @SerializedName("default")
    private Boolean defaultVariant;
    @SerializedName("first_sale_date")
    private Date firstSaleDate;
    private Long price;
    @SerializedName("first_active_date")
    private Date firstActiveDate;
    @SerializedName("old_price")
    private Long oldPrice;
    @SerializedName("retail_price")
    private Long retailPrice;
    private Long id;
    private Integer quantity;
    @SerializedName("default_image")
    private List<Image> images;
    private Attributes attributes;

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public Boolean getDefaultVariant() {
        return defaultVariant;
    }

    public void setDefaultVariant(Boolean defaultVariant) {
        this.defaultVariant = defaultVariant;
    }

    public Date getFirstSaleDate() {
        return firstSaleDate;
    }

    public void setFirstSaleDate(Date firstSaleDate) {
        this.firstSaleDate = firstSaleDate;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Date getFirstActiveDate() {
        return firstActiveDate;
    }

    public void setFirstActiveDate(Date firstActiveDate) {
        this.firstActiveDate = firstActiveDate;
    }

    public Long getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(Long oldPrice) {
        this.oldPrice = oldPrice;
    }

    public Long getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(Long retailPrice) {
        this.retailPrice = retailPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }
}
