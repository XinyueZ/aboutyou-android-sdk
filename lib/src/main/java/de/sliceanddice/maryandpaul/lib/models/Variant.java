package de.sliceanddice.maryandpaul.lib.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Variant {

    @SerializedName("updated_date")
    private Date updatedDate;
    private String ean;
    @SerializedName("default")
    private boolean defaultVariant;
    @SerializedName("first_sale_date")
    private Date firstSaleDate;
    private long price;
    @SerializedName("first_active_date")
    private Date firstActiveDate;
    @SerializedName("old_price")
    private long oldPrice;
    @SerializedName("retail_price")
    private long retailPrice;
    private long id;
    private int quantity;
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

    public boolean getDefaultVariant() {
        return defaultVariant;
    }

    public void setDefaultVariant(boolean defaultVariant) {
        this.defaultVariant = defaultVariant;
    }

    public Date getFirstSaleDate() {
        return firstSaleDate;
    }

    public void setFirstSaleDate(Date firstSaleDate) {
        this.firstSaleDate = firstSaleDate;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Date getFirstActiveDate() {
        return firstActiveDate;
    }

    public void setFirstActiveDate(Date firstActiveDate) {
        this.firstActiveDate = firstActiveDate;
    }

    public long getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(long oldPrice) {
        this.oldPrice = oldPrice;
    }

    public long getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(long retailPrice) {
        this.retailPrice = retailPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
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
