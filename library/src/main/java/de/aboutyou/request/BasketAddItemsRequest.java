package de.aboutyou.request;

import java.util.ArrayList;
import java.util.List;

import de.aboutyou.ShopApiClient;
import de.aboutyou.models.AddOrderLine;
import de.aboutyou.models.OrderLine;

public class BasketAddItemsRequest extends BasketModifyRequest {

    public static class Builder extends CollinsRequest.Builder<BasketAddItemsRequest> {

        private String sessionId;
        private long variantId = DEFAULT_LONG;
        private int amount = DEFAULT_INT;

        /**
         * Constructs a new Builder for a {@link BasketAddItemsRequest}
         * @param sessionId A session id provided by the caller, not null or empty
         */
        public Builder(String sessionId) {
            validateNotEmpty(sessionId, "sessionId");
            this.sessionId = sessionId;
        }

        /** Set the variantId to be added */
        public Builder setVariantId(long variantId) {
            this.variantId = variantId;
            return this;
        }

        /** Set the amount of items to be added */
        public Builder setAmount(int amount) {
            this.amount = amount;
            return this;
        }

        /** {@inheritDoc} */
        public BasketAddItemsRequest build() {
            validateSet(variantId, "variantId");
            validateSet(amount, "amount");

            BasketAddItemsRequest basketAddItemsRequest = new BasketAddItemsRequest();

            Basket basket = new Basket();
            basket.sessionId = sessionId;

            List<OrderLine> orderLines = new ArrayList<>();
            for (int i=0; i<amount; i++) {
                orderLines.add(new AddOrderLine(ShopApiClient.Helper.generateBasketId(), variantId));
            }
            basket.orderLines = orderLines;

            basketAddItemsRequest.basket = basket;

            return basketAddItemsRequest;
        }

    }

}
