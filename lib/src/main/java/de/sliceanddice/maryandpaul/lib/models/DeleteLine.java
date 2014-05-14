package de.sliceanddice.maryandpaul.lib.models;

import com.google.gson.annotations.SerializedName;

public class DeleteLine {

    @SerializedName("delete")
    private String id;

    public DeleteLine(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
