package de.sliceanddice.maryandpaul.lib.models;

import com.google.gson.annotations.SerializedName;

public class AddOrderLine extends OrderLine {

    private String id;
    @SerializedName("variant_id")
    private long variantId;

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
