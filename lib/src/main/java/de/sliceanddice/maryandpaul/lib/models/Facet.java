package de.sliceanddice.maryandpaul.lib.models;

import com.google.gson.annotations.SerializedName;

public class Facet {

    public String name;
    @SerializedName("facet_id")
    public long facetId;
    public long id;
    public String value;
    @SerializedName("group_name")
    public String groupName;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
