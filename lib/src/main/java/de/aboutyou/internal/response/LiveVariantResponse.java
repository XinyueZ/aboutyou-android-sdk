package de.aboutyou.internal.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import de.aboutyou.models.LiveVariant;

public class LiveVariantResponse extends CollinsResponse<List<LiveVariant>> {

    @SerializedName("live_variant")
    private List<LiveVariant> liveVariant;

    @Override
    public List<LiveVariant> get() {
        return liveVariant;
    }
}
