package de.sliceanddice.maryandpaul.lib.request;

import java.util.List;

import de.sliceanddice.maryandpaul.lib.enums.ProductFields;

public class ProductsRequest extends CollinsRequest {

    private Products products;

    private static class Products {

        private List<Long> ids;
        private List<ProductFields> fields;

    }

    public static class Builder {

        private List<Long> ids;
        private List<ProductFields> fields;

        public Builder filterByProductIds(List<Long> productIds) {
            this.ids = productIds;
            return this;
        }

        public Builder listFields(List<ProductFields> fields) {
            this.fields = fields;
            return this;
        }

        public ProductsRequest build() {
            ProductsRequest productRequest = new ProductsRequest();

            Products products = new Products();
            products.ids = ids;
            products.fields = fields;
            productRequest.products = products;

            return productRequest;
        }

    }

}