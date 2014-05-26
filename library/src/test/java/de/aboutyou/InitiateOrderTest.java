package de.aboutyou;

import org.junit.Test;

import de.aboutyou.models.Basket;
import de.aboutyou.models.InitiateOrder;
import de.aboutyou.request.BasketGetRequest;
import de.aboutyou.request.InitiateOrderRequest;
import de.aboutyou.util.MockClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class InitiateOrderTest extends TestBase {

    @Test
    public void testValidRequest() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidRequestMockClient());

        InitiateOrderRequest initiateOrderRequest = new InitiateOrderRequest.Builder("foobar")
                .setSuccessUrl("foo://bar")
                .build();

        InitiateOrder initiateOrder = shopApiClient.requestInitiateOrder(initiateOrderRequest);
        assertNotNull(initiateOrder);
        assertEquals(initiateOrder.getUrl(), "foo://bar");
        assertEquals(initiateOrder.getUserToken(), "1a2b");
        assertEquals(initiateOrder.getAppToken(), "3c4d");
    }

    private class ValidRequestMockClient extends MockClient {

        @Override
        protected void validateRequestBody(String requestBody) {
            assertEquals("[{\"initiate_order\":{\"session_id\":\"foobar\",\"success_url\":\"foo://bar\"}}]", requestBody);
        }

        @Override
        protected String getResponse() {
            return "[{\"initiate_order\":{\"url\":\"foo://bar\",\"user_token\":\"1a2b\",\"app_token\":\"3c4d\"}}]";
        }

    }
}