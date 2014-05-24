package de.aboutyou.models;

import com.google.gson.annotations.SerializedName;

public class LiveVariant extends BaseModel {

    private long id;
    @SerializedName("product_id")
    private long productId;
    @SerializedName("available_stock")
    private int availableStock;
    private int price;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
