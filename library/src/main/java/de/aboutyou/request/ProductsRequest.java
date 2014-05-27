package de.aboutyou.request;

import java.util.List;

import de.aboutyou.enums.ProductFields;

public class ProductsRequest extends CollinsRequest {

    private Products products;

    private static class Products {

        private List<Long> ids;
        private List<ProductFields> fields;

    }

    public static class Builder extends CollinsRequest.Builder<ProductsRequest> {

        private List<Long> ids;
        private List<ProductFields> fields;

        /** Filter result by product ids */
        public Builder filterByProductIds(List<Long> productIds) {
            this.ids = productIds;
            return this;
        }

        /** Request additional fields in the response */
        public Builder listFields(List<ProductFields> fields) {
            this.fields = fields;
            return this;
        }

        /** {@inheritDoc} */
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