package de.aboutyou.request;

import java.util.List;

import de.aboutyou.enums.ProductFields;

public class ProductsRequest extends CollinsRequest {

    private Products products;

    private static class Products {

        private List<Long> ids;
        private List<ProductFields> fields;

    }

    public static class Builder extends CollinsRequest.Builder {

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