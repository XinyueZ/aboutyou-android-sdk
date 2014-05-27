package de.aboutyou.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LiveVariantRequest extends CollinsRequest {

    @SerializedName("live_variant")
    private LiveVariant liveVariant;

    private static class LiveVariant {

        private List<Long> ids;

    }

    public static class Builder extends CollinsRequest.Builder<LiveVariantRequest> {

        private List<Long> ids;

        public Builder filterByVariantIds(List<Long> variantIds) {
            this.ids = variantIds;
            return this;
        }

        /** {@inheritDoc} */
        public LiveVariantRequest build() {
            LiveVariantRequest liveVariantRequest = new LiveVariantRequest();

            LiveVariant liveVariant = new LiveVariant();
            liveVariant.ids = ids;
            liveVariantRequest.liveVariant = liveVariant;

            return liveVariantRequest;
        }
    }

}