package de.aboutyou;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import de.aboutyou.models.Product;
import de.aboutyou.request.ProductsRequest;
import de.aboutyou.util.MockClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ProductsTest extends TestBase {

    @Test
    public void testValidRequest() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidRequestMockClient());

        ProductsRequest productsRequest = new ProductsRequest.Builder()
                .filterByProductIds(Arrays.asList(1l))
                .build();

        List<Product> products = shopApiClient.requestProducts(productsRequest);

        assertNotNull(products);
        assertTrue(products.size() == 1);
        assertEquals("Product 1", products.get(0).getName());
    }

    private class ValidRequestMockClient extends MockClient {

        @Override
        protected void validateRequestBody(String requestBody) {
            assertEquals("[{\"products\":{\"ids\":[1]}}]", requestBody);
        }

        @Override
        protected String getResponse() {
            return "[{\"products\":{\"ids\":{\"1\":{\"name\":\"Product 1\",\"active\":true,\"id\":1}}}}]";
        }

    }
}