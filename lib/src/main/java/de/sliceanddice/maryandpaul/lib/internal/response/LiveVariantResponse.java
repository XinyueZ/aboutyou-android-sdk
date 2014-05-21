package de.sliceanddice.maryandpaul.lib.internal.response;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import de.sliceanddice.maryandpaul.lib.models.LiveVariant;

public class LiveVariantResponse extends CollinsResponse<List<LiveVariant>> {

    @SerializedName("live_variant")
    private List<LiveVariant> liveVariant;

    @Override
    public List<LiveVariant> get() {
        return liveVariant;
    }
}
