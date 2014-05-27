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

    public static class Builder extends CollinsRequest.Builder<InitiateOrderRequest> {

        private String sessionId;
        private String successUrl;

        /**
         * Constructs a new Builder for an {@link de.aboutyou.request.InitiateOrderRequest}
         * <p>
         * You need to provide a successUrl using {@link de.aboutyou.request.InitiateOrderRequest.Builder#setSuccessUrl(String)}
         *
         * @param sessionId A session id provided by the caller, not null or empty
         */
        public Builder(String sessionId) {
            validateNotEmpty(sessionId, "sessionId");
            this.sessionId = sessionId;
        }

        public Builder setSuccessUrl(String successUrl) {
            validateNotEmpty(successUrl, "successUrl");
            this.successUrl = successUrl;
            return this;
        }

        /** {@inheritDoc} */
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
