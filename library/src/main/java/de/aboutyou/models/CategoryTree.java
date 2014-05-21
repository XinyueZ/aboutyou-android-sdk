package de.aboutyou.models;

import java.util.ArrayList;
import java.util.List;

public class CategoryTree {

    private List<Category> allCategories;
    private List<Category> activeCategories;

    public CategoryTree(List<Category> categories) {
        setupRelations(categories, null);

        allCategories = categories;
        activeCategories = new ArrayList<>();
        for (Category subCategory : allCategories) {
            if (subCategory.isActive()) {
                activeCategories.add(subCategory);
            }
        }
    }

    private void setupRelations(List<Category> categories, Category parent) {
        for (Category category : categories) {
            category.setParent(parent);
            setupRelations(category.getAllSubCategories(), category);
        }
    }

    public List<Category> getAllCategories() {
        return allCategories;
    }

    public void setAllCategories(List<Category> allCategories) {
        this.allCategories = allCategories;
    }

    public List<Category> getActiveCategories() {
        return activeCategories;
    }

    public void setActiveCategories(List<Category> activeCategories) {
        this.activeCategories = activeCategories;
    }
}
