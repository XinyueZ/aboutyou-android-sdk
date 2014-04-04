package de.sliceanddice.maryandpaul.lib.internal.response;

import com.google.gson.annotations.SerializedName;

import de.sliceanddice.maryandpaul.lib.models.ProductSearch;


public class ProductSearchResponse implements CollinsResponse<ProductSearch> {

    @SerializedName("product_search")
    private ProductSearch productSearch;

    public ProductSearch get() {
        return productSearch;
    }

}
