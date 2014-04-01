package de.sliceanddice.maryandpaul.lib.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category {

    private Long id;
    private String name;
    private Boolean active;
    private Integer position;
    private Category parentObject;
    @SerializedName("parent")
    private Long parentId;
    @SerializedName("sub_categories")
    private List<Category> allSubCategories;
    private List<Category> activeSubCategories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public Category getParent() {
        return parentObject;
    }

    public void setParent(Category parentObject) {
        this.parentObject = parentObject;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<Category> getAllSubCategories() {
        return allSubCategories;
    }

    public void setAllSubCategories(List<Category> allSubCategories) {
        this.allSubCategories = allSubCategories;
    }

    public List<Category> getActiveSubCategories() {
        return activeSubCategories;
    }

    public void setActiveSubCategories(List<Category> activeSubCategories) {
        this.activeSubCategories = activeSubCategories;
    }
}
