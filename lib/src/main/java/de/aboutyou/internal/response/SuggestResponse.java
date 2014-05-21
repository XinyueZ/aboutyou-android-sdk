package de.aboutyou.internal.response;

import com.google.gson.annotations.SerializedName;

import de.aboutyou.models.Suggest;

public class SuggestResponse extends CollinsResponse<Suggest> {

    @SerializedName("suggest")
    private Suggest suggests;

    public Suggest get() {
        return suggests;
    }
}
