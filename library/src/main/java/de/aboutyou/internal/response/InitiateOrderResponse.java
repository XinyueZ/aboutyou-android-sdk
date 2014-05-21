package de.aboutyou.internal.response;

import com.google.gson.annotations.SerializedName;

import de.aboutyou.models.InitiateOrder;

public class InitiateOrderResponse extends CollinsResponse<InitiateOrder> {

    @SerializedName("initiate_order")
    private InitiateOrder initiateOrder;

    @Override
    public InitiateOrder get() {
        return initiateOrder;
    }

}
