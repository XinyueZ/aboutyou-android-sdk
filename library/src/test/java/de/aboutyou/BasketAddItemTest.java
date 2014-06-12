package de.aboutyou;

import org.junit.Test;

import de.aboutyou.models.Basket;
import de.aboutyou.request.BasketAddItemsRequest;
import de.aboutyou.util.MockClient;

import static org.hamcrest.CoreMatchers.containsString;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertThat;

public class BasketAddItemTest extends TestBase {

    @Test
    public void testValidRequest() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidRequestMockClient());

        BasketAddItemsRequest basketAddItemsRequest = new BasketAddItemsRequest.Builder("foobar")
                .setVariantId(1l)
                .setAmount(1)
                .build();
        shopApiClient.requestAddItemsToBasket(basketAddItemsRequest);
    }

    private class ValidRequestMockClient extends MockClient {

        @Override
        protected void validateRequestBody(String requestBody) {
            assertThat(requestBody, containsString("[{\"basket\":{\"session_id\":\"foobar\",\"order_lines\":[{\"id\":\""));
            assertThat(requestBody, containsString("\",\"variant_id\":1}]}}]"));
        }

        @Override
        protected String getResponse() {
            return "[{\"basket\":{\"order_lines\":[]}}]";
        }

    }

    @Test(expected = IllegalArgumentException.class)
    public void testMissingSessionIdRequest() {
        new BasketAddItemsRequest.Builder(null).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMissingVariantId() {
        new BasketAddItemsRequest.Builder("foobar").setAmount(1).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMissingAmount() {
        new BasketAddItemsRequest.Builder("foobar").setVariantId(1l).build();
    }

    @Test
    public void testValidReponse() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidResponseMockClient());

        BasketAddItemsRequest basketAddItemsRequest = new BasketAddItemsRequest.Builder("foobar")
                .setVariantId(0l)
                .setAmount(0)
                .build();
        Basket basket = shopApiClient.requestAddItemsToBasket(basketAddItemsRequest);

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