package de.aboutyou.request;

import java.util.List;

import de.aboutyou.enums.ProductFields;

public class ProductEansRequest extends CollinsRequest {

    private Products products_eans;

    private static class Products {

        private List<Long> eans;
        private List<ProductFields> fields;

    }

    public static class Builder extends CollinsRequest.Builder<ProductEansRequest> {

        private List<Long> eans;
        private List<ProductFields> fields;

        /** Filter result by product ids */
        public Builder filterByEans(List<Long> eans) {
            validateNotEmpty(eans, "eans");
            this.eans = eans;
            return this;
        }

        /** Request additional fields in the response */
        public Builder listFields(List<ProductFields> fields) {
            validateNotEmpty(fields, "fields");
            this.fields = fields;
            return this;
        }

        /** {@inheritDoc} */
        public ProductEansRequest build() {
            ProductEansRequest productEansRequest = new ProductEansRequest();

            Products products = new Products();
            products.eans = eans;
            products.fields = fields;
            productEansRequest.products_eans = products;

            return productEansRequest;
        }

    }

}