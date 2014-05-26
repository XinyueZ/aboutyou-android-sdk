package de.aboutyou;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import de.aboutyou.models.Category;
import de.aboutyou.request.CategoriesRequest;
import de.aboutyou.util.MockClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CategoriesTest extends TestBase {

    @Test
    public void testSuccess() {
        ShopApiClient shopApiClient = getNewApiClient(new SuccessMockClient());

        CategoriesRequest categoriesRequest = new CategoriesRequest.Builder()
                .filterByCategoryIds(Arrays.asList(1l, 2l))
                .build();

        List<Category> categories = shopApiClient.requestCategories(categoriesRequest);

        assertNotNull(categories);
        assertTrue(categories.size() == 2);
        assertEquals("Cat1", categories.get(0).getName());
    }

    @Test
    public void testUnknownCategory() {
        ShopApiClient shopApiClient = getNewApiClient(new FailureMockClient());

        CategoriesRequest categoriesRequest = new CategoriesRequest.Builder()
                .filterByCategoryIds(Arrays.asList(1l))
                .build();

        List<Category> categories = shopApiClient.requestCategories(categoriesRequest);

        assertNotNull(categories);
        assertTrue(categories.size() == 1);
        assertTrue(categories.get(0).getErrorCode() == 404);
        assertTrue(categories.get(0).getErrorMessages().size() == 1);
        assertEquals("category not found", categories.get(0).getErrorMessages().get(0));
    }

    private class SuccessMockClient extends MockClient {

        @Override
        protected void validateRequestBody(String requestBody) {
            assertEquals(requestBody, "[{\"category\":{\"ids\":[1,2]}}]");
        }

        @Override
        protected String getResponse() {
            return "[{\"category\":{\"1\":{\"active\":true,\"position\":1,\"name\":\"Cat1\",\"parent\":0,\"id\":1},\"2\":{\"active\":true,\"position\":2,\"name\":\"Cat2\",\"parent\":0,\"id\":2}}}]";
        }

    }

    private class FailureMockClient extends MockClient {

        @Override
        protected String getResponse() {
            return "[{\"category\":{\"1\":{\"error_message\":[\"category not found\"],\"error_code\":404}}}]";
        }
    }
}