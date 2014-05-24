package de.aboutyou.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductSearch extends BaseModel {

    @SerializedName("product_count")
    private int productCount;
    private Facets facets;
    private List<Product> products;

    public int getProductCount() {
        return productCount;
    }

    public void setProductCount(int productCount) {
        this.productCount = productCount;
    }

    public Facets getFacets() {
        return facets;
    }

    public void setFacets(Facets facets) {
        this.facets = facets;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    private class Facets {
        private Prices prices;
        private Sale sale;
        private List<Term> categories;
    }

    private class Prices {

        private List<Range> ranges;

        public List<Range> getRanges() {
            return ranges;
        }

        public void setRanges(List<Range> ranges) {
            this.ranges = ranges;
        }

        private class Range {
            private int count;
            private int from;
            private Integer min;
            private Integer max;
            private Integer to;
            @SerializedName("total_count")
            private int totalCount;
            private long total;
            private double mean;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public int getFrom() {
                return from;
            }

            public void setFrom(int from) {
                this.from = from;
            }

            public Integer getMin() {
                return min;
            }

            public void setMin(Integer min) {
                this.min = min;
            }

            public Integer getMax() {
                return max;
            }

            public void setMax(Integer max) {
                this.max = max;
            }

            public Integer getTo() {
                return to;
            }

            public void setTo(Integer to) {
                this.to = to;
            }

            public int getTotalCount() {
                return totalCount;
            }

            public void setTotalCount(int totalCount) {
                this.totalCount = totalCount;
            }

            public long getTotal() {
                return total;
            }

            public void setTotal(long total) {
                this.total = total;
            }

            public double getMean() {
                return mean;
            }

            public void setMean(double mean) {
                this.mean = mean;
            }
        }
    }

    private class Sale {

        private int total;
        private int other;
        private int missing;
        private List<Term> terms;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getOther() {
            return other;
        }

        public void setOther(int other) {
            this.other = other;
        }

        public int getMissing() {
            return missing;
        }

        public void setMissing(int missing) {
            this.missing = missing;
        }

        public List<Term> getTerms() {
            return terms;
        }

        public void setTerms(List<Term> terms) {
            this.terms = terms;
        }

        private class Term {
            private int count;
            private String term;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getTerm() {
                return term;
            }

            public void setTerm(String term) {
                this.term = term;
            }
        }
    }

    private class Term {

        private int count;
        private int term;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getTerm() {
            return term;
        }

        public void setTerm(int term) {
            this.term = term;
        }
    }
}
