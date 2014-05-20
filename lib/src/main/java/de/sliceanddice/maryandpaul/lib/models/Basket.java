package de.sliceanddice.maryandpaul.lib.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class Basket {

    @SerializedName("total_price")
    private Long totalPrice;
    @SerializedName("total_net")
    private Long totalNet;
    @SerializedName("total_vat")
    private Long totalVat;
    private Map<Long, Product> products;
    @SerializedName("order_lines")
    private List<OrderLine> orderLines;

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Long getTotalNet() {
        return totalNet;
    }

    public void setTotalNet(Long totalNet) {
        this.totalNet = totalNet;
    }

    public Long getTotalVat() {
        return totalVat;
    }

    public void setTotalVat(Long totalVat) {
        this.totalVat = totalVat;
    }

    public Map<Long, Product> getProducts() {
        return products;
    }

    public void setProducts(Map<Long, Product> products) {
        this.products = products;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
    }
}
