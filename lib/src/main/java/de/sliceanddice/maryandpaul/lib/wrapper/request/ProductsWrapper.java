package de.sliceanddice.maryandpaul.lib.wrapper.request;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.sliceanddice.maryandpaul.lib.models.Product;

public class ProductsWrapper extends BaseWrapper {

    @SerializedName("products")
    private Products productsResult;

    private static class Products {

        @SerializedName("ids")
        private Map<Long, Product> products;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(productsResult.products.values());
    }
}
