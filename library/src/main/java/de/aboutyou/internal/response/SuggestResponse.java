package de.aboutyou.internal.response;

import com.google.gson.annotations.SerializedName;

import de.aboutyou.models.Suggest;

public class SuggestResponse extends CollinsResponse<Suggest> {

    @SerializedName("suggest")
    private Suggest suggests;

    @Override
    public Suggest get() {
        return suggests;
    }
}
