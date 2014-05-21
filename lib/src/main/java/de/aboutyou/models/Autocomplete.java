package de.aboutyou.models;

import java.util.ArrayList;
import java.util.List;

public class Autocomplete {

    private List<Product> products;
    private List<Category> categories;

    public List<Product> getProducts() {
        if (products == null) {
            products = new ArrayList<>();
        }
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Category> getCategories() {
        if (categories == null) {
            categories = new ArrayList<>();
        }
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
