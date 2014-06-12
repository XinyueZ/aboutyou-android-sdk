package de.aboutyou;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import de.aboutyou.models.Basket;
import de.aboutyou.request.BasketRemoveItemsRequest;
import de.aboutyou.util.MockClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BasketRemoveItemTest extends TestBase {

    @Test
    public void testValidRequest() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidRequestMockClient());

        BasketRemoveItemsRequest basketRemoveItemsRequest = new BasketRemoveItemsRequest.Builder("foobar")
                .setItemIds(Arrays.asList("id1"))
                .build();
        shopApiClient.requestRemoveItemsFromBasket(basketRemoveItemsRequest);
    }

    private class ValidRequestMockClient extends MockClient {

        @Override
        protected void validateRequestBody(String requestBody) {
            assertEquals("[{\"basket\":{\"session_id\":\"foobar\",\"order_lines\":[{\"delete\":\"id1\"}]}}]", requestBody);
        }

        @Override
        protected String getResponse() {
            return "[{\"basket\":{\"order_lines\":[]}}]";
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMissingSessionIdRequest() {
        new BasketRemoveItemsRequest.Builder(null).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMissingItemIds() {
        new BasketRemoveItemsRequest.Builder("foobar").build();
    }

    @Test
    public void testValidReponse() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidResponseMockClient());

        BasketRemoveItemsRequest basketRemoveItemsRequest = new BasketRemoveItemsRequest.Builder("foobar")
                .setItemIds(Arrays.asList("id1"))
                .build();
        Basket basket = shopApiClient.requestRemoveItemsFromBasket(basketRemoveItemsRequest);

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