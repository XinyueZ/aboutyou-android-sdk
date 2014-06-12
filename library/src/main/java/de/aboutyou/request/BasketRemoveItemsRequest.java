package de.aboutyou.request;

import java.util.ArrayList;
import java.util.List;

import de.aboutyou.models.DeleteOrderLine;
import de.aboutyou.models.OrderLine;

public class BasketRemoveItemsRequest extends BasketModifyRequest {

    public static class Builder extends CollinsRequest.Builder<BasketRemoveItemsRequest> {

        private String sessionId;
        private List<String> itemIds;

        /**
         * Constructs a new Builder for a {@link BasketRemoveItemsRequest}
         * @param sessionId A session id provided by the caller, not null or empty
         */
        public Builder(String sessionId) {
            validateNotEmpty(sessionId, "sessionId");
            this.sessionId = sessionId;
        }

        /** Set the itemIds to be removed */
        public Builder setItemIds(List<String> itemIds) {
            validateNotEmpty(itemIds, "itemIds");
            this.itemIds = itemIds;
            return this;
        }

        /** {@inheritDoc} */
        public BasketRemoveItemsRequest build() {
            validateNotEmpty(itemIds, "itemIds");

            BasketRemoveItemsRequest basketAddRequest = new BasketRemoveItemsRequest();

            Basket basket = new Basket();
            basket.sessionId = sessionId;

            List<OrderLine> orderLines = new ArrayList<>();
            for (String itemId : itemIds) {
                orderLines.add(new DeleteOrderLine(itemId));
            }
            basket.orderLines = orderLines;

            basketAddRequest.basket = basket;

            return basketAddRequest;
        }

    }

}
