package de.sliceanddice.maryandpaul.lib.requests;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import de.sliceanddice.maryandpaul.lib.enums.Direction;
import de.sliceanddice.maryandpaul.lib.enums.FacetGroup;
import de.sliceanddice.maryandpaul.lib.enums.ProductFilter;
import de.sliceanddice.maryandpaul.lib.enums.Sortby;

public class ProductSearchRequest extends BaseRequest {

    @SerializedName("product_search")
    private ProductSearch productSearch = new ProductSearch();

    private static class ProductSearch {
        @SerializedName("session_id")
        private String sessionId;
        private Filter filter;
        private Result result;

        private static class Filter {
            private List<Long> categories;
            // true = sale only, false = non-sale only, NULL = all
            private Boolean sale;
            @SerializedName("prices")
            private Prices priceRange;
            private String searchword;
            private List<FacetGroup> facets;

            private static class Prices {
                private Long from;
                private Long to;

                public Prices(Long from, Long to) {
                    this.from = from;
                    this.to = to;
                }
            }
        }

        private static class Result {
            private Sort sort;
            @SerializedName("price")
            private Boolean showPriceInformation;
            @SerializedName("sale")
            private Boolean noIdeaWhatThisIsFor;
            private Integer limit;
            private Integer offset;
            @SerializedName("categories")
            private Boolean showCategories;

            private static class Sort {
                private Sortby by;
                private Direction direction;

                public Sort(Sortby by, Direction direction) {
                    this.by = by;
                    this.direction = direction;
                }
            }
        }
    }

    public static class Builder {

        private String sessionId;
        private List<Long> categories;
        private ProductFilter productFilter;
        private Long priceFrom;
        private Long priceTo;
        private String searchString;
        private List<FacetGroup> facets;
        private Boolean listPriceDetails;
        private Boolean listCategories;
        private Integer limit;
        private Integer offset;
        private Sortby sortby;
        private Direction sortDirection;

        public Builder(String sessionId) {
            if (sessionId == null) {
                throw new IllegalArgumentException("sessionId must not be null");
            }
            this.sessionId = sessionId;
        }

        public Builder filterByCategories(List<Long> categories) {
            this.categories = categories;
            return this;
        }

        public Builder filterByStatus(ProductFilter productFilter) {
            this.productFilter = productFilter;
            return this;
        }

        public Builder filterByMinPrice(long priceInCents) {
            this.priceFrom = priceInCents;
            return this;
        }

        public Builder filterByMaxPrice(long priceInCents) {
            this.priceTo = priceInCents;
            return this;
        }

        public Builder filterBySearchString(String searchString) {
            this.searchString = searchString;
            return this;
        }

        public Builder filterByFacets(List<FacetGroup> facets) {
            this.facets = facets;
            return this;
        }

        public Builder listPriceDetails(boolean listPriceDetails) {
            this.listPriceDetails = listPriceDetails;
            return this;
        }

        public Builder listCategoriesWithResults(boolean listCategories) {
            this.listCategories = listCategories;
            return this;
        }

        public Builder limit(Integer limit) {
            this.limit = limit;
            return this;
        }

        public Builder offset(Integer offset) {
            this.offset = offset;
            return this;
        }

        public Builder sortBy(Sortby sortby) {
            this.sortby = sortby;
            return this;
        }

        public Builder sortDirection(Direction direction) {
            this.sortDirection = direction;
            return this;
        }

        public ProductSearchRequest build() {
            ProductSearch productSearch = new ProductSearch();
            productSearch.sessionId = sessionId;

            ProductSearch.Filter filter = new ProductSearch.Filter();
            filter.categories = categories;
            filter.facets = facets;
            filter.priceRange = new ProductSearch.Filter.Prices(priceFrom, priceTo);
            filter.searchword = searchString;
            filter.sale = productFilter.getValue();
            productSearch.filter = filter;

            ProductSearch.Result result = new ProductSearch.Result();
            result.sort = new ProductSearch.Result.Sort(sortby, sortDirection);
            result.limit = limit;
            result.offset = offset;
            result.showCategories = listCategories;
            result.showPriceInformation = listPriceDetails;

            ProductSearchRequest productSearchRequest = new ProductSearchRequest();
            productSearchRequest.productSearch = productSearch;

            return productSearchRequest;
        }

    }
}
