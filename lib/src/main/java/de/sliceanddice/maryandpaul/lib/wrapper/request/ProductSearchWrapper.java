package de.sliceanddice.maryandpaul.lib.wrapper.request;

import com.google.gson.annotations.SerializedName;

import de.sliceanddice.maryandpaul.lib.models.ProductSearch;


public class ProductSearchWrapper extends BaseWrapper {

    @SerializedName("product_search")
    private ProductSearch productSearch;

    public ProductSearch getProductSearch() {
        return productSearch;
    }

}
