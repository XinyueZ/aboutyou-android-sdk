package de.aboutyou.internal.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.aboutyou.models.Product;

public class ProductsResponse extends CollinsResponse<List<Product>> {

    @SerializedName("products")
    private Products productsResult;

    private static class Products {

        @SerializedName("ids")
        private Map<Long, Product> products;
    }

    public List<Product> get() {
        return new ArrayList<>(productsResult.products.values());
    }
}
