package de.aboutyou.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Basket extends BaseModel {

    @SerializedName("total_price")
    private long totalPrice;
    @SerializedName("total_net")
    private long totalNet;
    @SerializedName("total_vat")
    private long totalVat;
    private Map<Long, Product> products;
    @SerializedName("order_lines")
    private List<AddOrderLine> orderLines;

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getTotalNet() {
        return totalNet;
    }

    public void setTotalNet(long totalNet) {
        this.totalNet = totalNet;
    }

    public long getTotalVat() {
        return totalVat;
    }

    public void setTotalVat(long totalVat) {
        this.totalVat = totalVat;
    }

    public Map<Long, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<Long, Product> products) {
        this.products = products;
    }

    public List<AddOrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<AddOrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}
