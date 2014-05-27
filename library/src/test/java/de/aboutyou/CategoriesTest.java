package de.aboutyou;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.aboutyou.models.Category;
import de.aboutyou.request.CategoriesRequest;
import de.aboutyou.util.MockClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CategoriesTest extends TestBase {

    @Test
    public void testValidRequest() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidRequestMockClient());

        CategoriesRequest categoriesRequest = new CategoriesRequest.Builder()
                .filterByCategoryIds(Arrays.asList(1l, 2l))
                .build();
        shopApiClient.requestCategories(categoriesRequest);
    }

    private class ValidRequestMockClient extends MockClient {

        @Override
        protected void validateRequestBody(String requestBody) {
            assertEquals(requestBody, "[{\"category\":{\"ids\":[1,2]}}]");
        }

        @Override
        protected String getResponse() {
            return "[{\"category\":{}}]";
        }

    }

    @Test
    public void testValidResponse() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidResponseMockClient());

        CategoriesRequest categoriesRequest = new CategoriesRequest.Builder()
                .filterByCategoryIds(Collections.<Long>emptyList())
                .build();

        List<Category> categories = shopApiClient.requestCategories(categoriesRequest);

        assertNotNull(categories);
        assertTrue(categories.size() == 2);
        assertEquals("Cat1", categories.get(0).getName());
    }

    private class ValidResponseMockClient extends MockClient {

        @Override
        protected String getResponse() {
            return "[{\"category\":{\"1\":{\"active\":true,\"position\":1,\"name\":\"Cat1\",\"parent\":0,\"id\":1},\"2\":{\"active\":true,\"position\":2,\"name\":\"Cat2\",\"parent\":0,\"id\":2}}}]";
        }

    }

    @Test
    public void testUnknownCategoryResponse() {
        ShopApiClient shopApiClient = getNewApiClient(new UnknownCategoryMockClient());

        CategoriesRequest categoriesRequest = new CategoriesRequest.Builder()
                .filterByCategoryIds(Collections.<Long>emptyList())
                .build();

        List<Category> categories = shopApiClient.requestCategories(categoriesRequest);

        assertNotNull(categories);
        assertTrue(categories.size() == 1);
        assertTrue(categories.get(0).getErrorCode() == 404);
        assertTrue(categories.get(0).getErrorMessages().size() == 1);
        assertEquals("category not found", categories.get(0).getErrorMessages().get(0));
    }

    private class UnknownCategoryMockClient extends MockClient {

        @Override
        protected String getResponse() {
            return "[{\"category\":{\"1\":{\"error_message\":[\"category not found\"],\"error_code\":404}}}]";
        }
    }
}