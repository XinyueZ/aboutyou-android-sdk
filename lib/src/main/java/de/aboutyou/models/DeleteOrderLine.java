package de.aboutyou.models;

import com.google.gson.annotations.SerializedName;

public class DeleteOrderLine extends OrderLine {

    @SerializedName("delete")
    private String id;

    public DeleteOrderLine(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
