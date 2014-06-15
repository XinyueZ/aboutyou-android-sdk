package de.aboutyou.internal.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.aboutyou.models.Product;

public class ProductEansResponse extends CollinsResponse<List<Product>> {

    @SerializedName("products_eans")
    private Products productsResult;

    private static class Products {

        @SerializedName("ids")
        private Map<Long, Product> products;
    }

    @Override
    public List<Product> get() {
        if (productsResult != null && productsResult.products != null) {
            return new ArrayList<>(productsResult.products.values());
        } else {
            return null;
        }
    }
}
