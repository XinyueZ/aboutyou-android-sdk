package de.aboutyou.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import de.aboutyou.models.OrderLine;

public class BasketModifyRequest extends CollinsRequest {

    protected Basket basket;

    protected static class Basket {
        @SerializedName("session_id")
        protected String sessionId;
        @SerializedName("order_lines")
        protected List<OrderLine> orderLines;
    }

    public static class Builder extends CollinsRequest.Builder<BasketModifyRequest> {

        private String sessionId;
        private List<OrderLine> orderLines;

        /**
         * Constructs a new Builder for a {@link de.aboutyou.request.BasketModifyRequest}
         * @param sessionId A session id provided by the caller, not null or empty
         */
        public Builder(String sessionId) {
            validateNotEmpty(sessionId, "sessionId");
            this.sessionId = sessionId;
        }

        /** Set the order lines to modify the basket */
        public Builder setOrderLines(List<OrderLine> orderLines) {
            validateNotEmpty(orderLines, "orderLines");
            this.orderLines = orderLines;
            return this;
        }

        /** {@inheritDoc} */
        public BasketModifyRequest build() {
            BasketModifyRequest basketModifyRequest = new BasketModifyRequest();

            Basket basket = new Basket();
            basket.sessionId = sessionId;
            basket.orderLines = orderLines;
            basketModifyRequest.basket = basket;

            return basketModifyRequest;
        }

    }

}
