package de.aboutyou.models;

import com.google.gson.annotations.SerializedName;

public class Facet {

    private String name;
    @SerializedName("facet_id")
    private long facetId;
    private String value;
    @SerializedName("group_name")
    private String groupName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getFacetId() {
        return facetId;
    }

    public void setFacetId(long facetId) {
        this.facetId = facetId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
