package de.aboutyou;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import de.aboutyou.models.AddOrderLine;
import de.aboutyou.models.Basket;
import de.aboutyou.models.OrderLine;
import de.aboutyou.request.BasketModifyRequest;
import de.aboutyou.util.MockClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BasketModifyTest extends TestBase {

    @Test
    public void testValidRequest() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidRequestMockClient());

        List<OrderLine> orderLines = new ArrayList<>();
        orderLines.add(new AddOrderLine("id1", 1l));

        BasketModifyRequest basketModifyRequest = new BasketModifyRequest.Builder("foobar")
                .setOrderLines(orderLines)
                .build();
        shopApiClient.requestModifyBasket(basketModifyRequest);
    }

    private class ValidRequestMockClient extends MockClient {

        @Override
        protected void validateRequestBody(String requestBody) {
            assertEquals("[{\"basket\":{\"session_id\":\"foobar\",\"order_lines\":[{\"id\":\"id1\",\"variant_id\":1}]}}]", requestBody);
        }

        @Override
        protected String getResponse() {
            return "[{\"basket\":{\"order_lines\":[]}}]";
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testMissingSessionIdRequest() {
        new BasketModifyRequest.Builder(null).build();
    }

    @Test
    public void testValidReponse() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidResponseMockClient());

        BasketModifyRequest basketModifyRequest = new BasketModifyRequest.Builder("foobar")
                .setOrderLines(Collections.<OrderLine>emptyList())
                .build();
        Basket basket = shopApiClient.requestModifyBasket(basketModifyRequest);

        assertNotNull(basket);
        assertTrue(basket.getOrderLines().size() == 2);
    }

    private class ValidResponseMockClient extends MockClient {

        @Override
        protected String getResponse() {
            return "[{\"basket\":{\"order_lines\":[{\"id\":\"id1\",\"variant_id\":1},{\"id\":\"id2\",\"variant_id\":2}]}}]";
        }

    }
}