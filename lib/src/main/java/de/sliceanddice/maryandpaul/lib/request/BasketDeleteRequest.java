package de.sliceanddice.maryandpaul.lib.request;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import de.sliceanddice.maryandpaul.lib.models.DeleteLine;
import de.sliceanddice.maryandpaul.lib.models.OrderLine;

public class BasketDeleteRequest implements CollinsRequest {

    private Basket basket;

    private static class Basket {
        @SerializedName("session_id")
        private String sessionId;
        @SerializedName("order_lines")
        private List<DeleteLine> deleteLines;
    }

    public static class Builder {

        private String sessionId;
        private List<DeleteLine> deleteLines;

        public Builder(String sessionId) {
            if (sessionId == null) {
                throw new IllegalArgumentException("sessionId must not be null");
            }
            this.sessionId = sessionId;
        }

        public Builder setDeleteLines(List<DeleteLine> deleteLines) {
            this.deleteLines = deleteLines;
            return this;
        }

        public BasketDeleteRequest build() {
            BasketDeleteRequest basketDeleteRequest = new BasketDeleteRequest();

            Basket basket = new Basket();
            basket.sessionId = sessionId;
            basket.deleteLines = deleteLines;
            basketDeleteRequest.basket = basket;

            return basketDeleteRequest;
        }

    }

}
