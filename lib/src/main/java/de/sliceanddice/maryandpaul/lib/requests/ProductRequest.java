package de.sliceanddice.maryandpaul.lib.requests;

import java.util.List;

public class ProductRequest extends BaseRequest {

    private Products products;

    private static class Products {

        private List<Long> ids;

    }

    public static class Builder {

        private List<Long> ids;

        public Builder filterByProductIds(List<Long> productIds) {
            this.ids = productIds;
            return this;
        }

        public ProductRequest build() {
            ProductRequest productRequest = new ProductRequest();

            Products products = new Products();
            products.ids = ids;
            productRequest.products = products;

            return productRequest;
        }

    }

}