package de.aboutyou.request;

import com.google.gson.annotations.SerializedName;

public class InitiateOrderRequest extends CollinsRequest {

    @SerializedName("initiate_order")
    private InititateOrder initiateOrder;

    private static class InititateOrder {
        @SerializedName("session_id")
        private String sessionId;
        @SerializedName("success_url")
        private String successUrl;
    }

    public static class Builder extends CollinsRequest.Builder {

        private String sessionId;
        private String successUrl;

        public Builder(String sessionId) {
            validateNotEmpty(sessionId, "sessionId");
            this.sessionId = sessionId;
        }

        public Builder setSuccessUrl(String successUrl) {
            validateNotEmpty(successUrl, "successUrl");
            this.successUrl = successUrl;
            return this;
        }

        public InitiateOrderRequest build() {
            validateNotEmpty(successUrl, "successUrl");
            InitiateOrderRequest basketAddRequest = new InitiateOrderRequest();

            InititateOrder initiateOrder = new InititateOrder();
            initiateOrder.sessionId = sessionId;
            initiateOrder.successUrl = successUrl;
            basketAddRequest.initiateOrder = initiateOrder;

            return basketAddRequest;
        }

    }

}
