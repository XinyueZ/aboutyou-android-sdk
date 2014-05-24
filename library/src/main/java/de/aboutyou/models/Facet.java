package de.aboutyou.models;

import com.google.gson.annotations.SerializedName;

public class Facet {

    private String name;
    @SerializedName("facet_id")
    private long facetId;
    @SerializedName("facet_id")
    private long facetGroupId;
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

    public long getFacetGroupId() {
        return facetGroupId;
    }

    public void setFacetGroupId(long facetGroupId) {
        this.facetGroupId = facetGroupId;
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
