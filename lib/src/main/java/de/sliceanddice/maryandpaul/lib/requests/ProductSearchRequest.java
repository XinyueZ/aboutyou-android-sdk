package de.sliceanddice.maryandpaul.lib.requests;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import de.sliceanddice.maryandpaul.lib.enums.Direction;
import de.sliceanddice.maryandpaul.lib.enums.FacetGroup;
import de.sliceanddice.maryandpaul.lib.enums.Sortby;

public class ProductSearchRequest extends BaseRequest {

    @SerializedName("product_search")
    private ProductSearch productSearch = new ProductSearch();

    private static class ProductSearch {
        @SerializedName("session_id")
        String sessionId;
        Filter filter;
        Result result;

        private static class Filter {
            List<Long> categories;
            Boolean sale;
            Prices prices = new Prices();
            String searchword;
            List<FacetGroup> facets;

            private static class Prices {
                Long from;
                Long to;
            }
        }

        private static class Result {
            Sort sort = new Sort();
            Boolean price;
            Boolean sale;
            Integer limit;
            Integer offset;
            Boolean categories;

            private static class Sort {
                Sortby by;
                Direction direction;
            }
        }
    }

    public static class Builder {

        private String sessionId;
        private Sortby sortby;
        private Direction direction;

        public Builder(String sessionId) {
            this.sessionId = sessionId;
        }

        public Builder sortBy(Sortby sortby) {
            this.sortby = sortby;
            return this;
        }

        public Builder sortDirection(Direction direction) {
            this.direction = direction;
            return this;
        }

        public ProductSearchRequest build() {
            ProductSearchRequest productSearchRequest = new ProductSearchRequest();
            productSearchRequest.productSearch.sessionId = sessionId;
            productSearchRequest.productSearch.result = new ProductSearch.Result();
            productSearchRequest.productSearch.result.sort = new ProductSearch.Result.Sort();
            productSearchRequest.productSearch.result.sort.by = sortby;
            productSearchRequest.productSearch.result.sort.direction = direction;
            return productSearchRequest;
        }

    }
}
