package de.aboutyou;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import de.aboutyou.models.AddOrderLine;
import de.aboutyou.models.Basket;
import de.aboutyou.models.CategoryTree;
import de.aboutyou.models.OrderLine;
import de.aboutyou.request.BasketGetRequest;
import de.aboutyou.request.BasketModifyRequest;
import de.aboutyou.util.MockClient;
import retrofit.client.Request;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class BasketGetTest extends TestBase {

    @Test
    public void testSuccess() {
        ShopApiClient shopApiClient = getNewApiClient(new SuccessMockClient());

        BasketGetRequest basketGetRequest = new BasketGetRequest.Builder("foobar")
                .build();

        Basket basket = shopApiClient.requestGetBasket(basketGetRequest);
        assertNotNull(basket);
        assertTrue(basket.getOrderLines().size() == 2);
    }

    private class SuccessMockClient extends MockClient {

        @Override
        protected String getResponse() {
            return "[{\"basket\":{\"order_lines\":[{\"id\":\"id1\",\"variant_id\":1},{\"id\":\"id2\",\"variant_id\":2}]}}]";
        }

    }
}