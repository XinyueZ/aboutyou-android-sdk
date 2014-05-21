package de.sliceanddice.maryandpaul.lib.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Category extends FallibleModel {

    private long id;
    private String name;
    private boolean active;
    private int position;
    private Category parentObject;
    @SerializedName("parent")
    private long parentId;
    @SerializedName("sub_categories")
    private List<Category> allSubCategories;
    private List<Category> activeSubCategories;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public Category getParent() {
        return parentObject;
    }

    public void setParent(Category parentObject) {
        this.parentObject = parentObject;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
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