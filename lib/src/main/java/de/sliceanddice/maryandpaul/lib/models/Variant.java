package de.sliceanddice.maryandpaul.lib.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

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

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public String getEan() {
        return ean;
    }

    public Boolean getDefaultVariant() {
        return defaultVariant;
    }

    public Date getFirstSaleDate() {
        return firstSaleDate;
    }

    public Long getPrice() {
        return price;
    }

    public Date getFirstActiveDate() {
        return firstActiveDate;
    }

    public Long getOldPrice() {
        return oldPrice;
    }

    public Long getRetailPrice() {
        return retailPrice;
    }

    public Long getId() {
        return id;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
