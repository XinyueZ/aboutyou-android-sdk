package de.sliceanddice.maryandpaul.lib.models;

import com.google.gson.annotations.SerializedName;

public class AddOrderLine extends OrderLine {

    protected String id;
    @SerializedName("variant_id")
    protected long variantId;

    public AddOrderLine(String id, Long variantId) {
        this.id = id;
        this.variantId = variantId;
    }

    public String getId() {
        return id;
    }

    public Long getVariantId() {
        return variantId;
    }
}
