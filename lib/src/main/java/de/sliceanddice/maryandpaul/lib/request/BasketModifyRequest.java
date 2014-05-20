package de.sliceanddice.maryandpaul.lib.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import de.sliceanddice.maryandpaul.lib.models.OrderLine;

public class BasketModifyRequest implements CollinsRequest {

    private Basket basket;

    private static class Basket {
        @SerializedName("session_id")
        private String sessionId;
        @SerializedName("order_lines")
        private List<OrderLine> orderLines;
    }

    public static class Builder {

        private String sessionId;
        private List<OrderLine> orderLines;

        public Builder(String sessionId) {
            if (sessionId == null) {
                throw new IllegalArgumentException("sessionId must not be null");
            }
            this.sessionId = sessionId;
        }

        public Builder setOrderLines(List<OrderLine> orderLines) {
            this.orderLines = orderLines;
            return this;
        }

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
