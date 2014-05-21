package de.aboutyou.internal.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.aboutyou.models.LiveVariant;

public class LiveVariantResponse extends CollinsResponse<List<LiveVariant>> {

    @SerializedName("live_variant")
    private Map<Long, LiveVariant> liveVariantsResult;

    @Override
    public List<LiveVariant> get() {
        return new ArrayList<>(liveVariantsResult.values());
    }
}
