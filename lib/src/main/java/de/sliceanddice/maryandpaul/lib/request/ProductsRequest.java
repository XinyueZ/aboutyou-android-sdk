package de.sliceanddice.maryandpaul.lib.request;

import java.util.List;

public class ProductsRequest implements CollinsRequest {

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

        public ProductsRequest build() {
            ProductsRequest productRequest = new ProductsRequest();

            Products products = new Products();
            products.ids = ids;
            productRequest.products = products;

            return productRequest;
        }

    }

}