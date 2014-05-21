package de.aboutyou.internal.response;

import com.google.gson.annotations.SerializedName;

import de.aboutyou.models.ProductSearch;

public class ProductSearchResponse extends CollinsResponse<ProductSearch> {

    @SerializedName("product_search")
    private ProductSearch productSearch;

    @Override
    public ProductSearch get() {
        return productSearch;
    }

}
