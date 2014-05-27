package de.aboutyou;

import org.junit.Test;

import de.aboutyou.models.Basket;
import de.aboutyou.request.BasketGetRequest;
import de.aboutyou.util.MockClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BasketGetTest extends TestBase {

    @Test
    public void testValidRequest() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidRequestMockClient());

        BasketGetRequest basketGetRequest = new BasketGetRequest.Builder("foobar").build();
        shopApiClient.requestGetBasket(basketGetRequest);
    }

    private class ValidRequestMockClient extends MockClient {

        @Override
        protected void validateRequestBody(String requestBody) {
            assertEquals("[{\"basket\":{\"session_id\":\"foobar\"}}]", requestBody);
        }

        @Override
        protected String getResponse() {
            return "[{\"basket\":{\"order_lines\":[]}}]";
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testMissingSessionIdRequest() {
        new BasketGetRequest.Builder(null).build();
    }

    @Test
    public void testValidResponse() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidResponseMockClient());

        BasketGetRequest basketGetRequest = new BasketGetRequest.Builder("foobar").build();
        Basket basket = shopApiClient.requestGetBasket(basketGetRequest);

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