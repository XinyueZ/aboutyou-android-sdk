package de.aboutyou;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import de.aboutyou.models.Category;
import de.aboutyou.models.ProductSearch;
import de.aboutyou.request.CategoriesRequest;
import de.aboutyou.request.ProductSearchRequest;
import de.aboutyou.util.MockClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ProductSearchTest extends TestBase {

    @Test
    public void testValidRequest() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidRequestMockClient());

        ProductSearchRequest productSearchRequest = new ProductSearchRequest.Builder("foobar")
                .filterByCategories(Arrays.asList(1l))
                .build();

        ProductSearch productSearch = shopApiClient.requestProductSearch(productSearchRequest);

        assertNotNull(productSearch);
        assertTrue(productSearch.getProductCount() == 1);
        assertEquals("Produkt 1", productSearch.getProducts().get(0).getName());
    }

    private class ValidRequestMockClient extends MockClient {

        @Override
        protected void validateRequestBody(String requestBody) {
            assertEquals("[{\"product_search\":{\"session_id\":\"foobar\",\"filter\":{\"categories\":[1],\"prices\":{}},\"result\":{\"sort\":{}}}}]", requestBody);
        }

        @Override
        protected String getResponse() {
            return "[{\"product_search\":{\"product_count\":1,\"facets\":{},\"products\":[{\"id\":1,\"name\":\"Produkt 1\"}]}}]";
        }

    }
}