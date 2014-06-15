package de.aboutyou;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import de.aboutyou.models.Product;
import de.aboutyou.request.ProductEansRequest;
import de.aboutyou.util.MockClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ProductEansTest extends TestBase {

    @Test
    public void testValidRequest() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidRequestMockClient());

        ProductEansRequest productEansRequest = new ProductEansRequest.Builder()
                .filterByEans(Arrays.asList(1l))
                .build();

        shopApiClient.requestProducts(productEansRequest);
    }

    private class ValidRequestMockClient extends MockClient {

        @Override
        protected void validateRequestBody(String requestBody) {
            assertEquals("[{\"products_eans\":{\"eans\":[1]}}]", requestBody);
        }

        @Override
        protected String getResponse() {
            return "[{\"products\":{}}]";
        }

    }

    @Test
    public void testValidResponse() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidResponseMockClient());

        ProductEansRequest productEansRequest = new ProductEansRequest.Builder()
                .filterByEans(Arrays.asList(1l))
                .build();

        List<Product> products = shopApiClient.requestProducts(productEansRequest);

        assertNotNull(products);
        assertTrue(products.size() == 1);
        assertEquals("Product 1", products.get(0).getName());
    }

    private class ValidResponseMockClient extends MockClient {

        @Override
        protected String getResponse() {
            return "[{\"products_eans\":{\"ids\":{\"1\":{\"name\":\"Product 1\",\"active\":true,\"id\":1}}}}]";
        }

    }
}