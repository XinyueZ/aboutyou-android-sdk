package de.sliceanddice.maryandpaul.lib.internal.response;

import com.google.gson.annotations.SerializedName;

import de.sliceanddice.maryandpaul.lib.models.InitiateOrder;

public class InitiateOrderResponse implements CollinsResponse<InitiateOrder> {

    @SerializedName("initiate_order")
    private InitiateOrder initiateOrder;

    @Override
    public InitiateOrder get() {
        return initiateOrder;
    }

}
