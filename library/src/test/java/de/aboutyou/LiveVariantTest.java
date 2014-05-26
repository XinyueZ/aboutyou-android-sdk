package de.aboutyou;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import de.aboutyou.models.LiveVariant;
import de.aboutyou.request.LiveVariantRequest;
import de.aboutyou.util.MockClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LiveVariantTest extends TestBase {

    @Test
    public void testValidRequest() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidRequestMockClient());

        LiveVariantRequest liveVariantRequest = new LiveVariantRequest.Builder()
                .filterByVariantIds(Arrays.asList(1l))
                .build();

        List<LiveVariant> liveVariants = shopApiClient.requestLiveVariants(liveVariantRequest);

        assertNotNull(liveVariants);
        assertTrue(liveVariants.size() == 1);
        assertEquals(liveVariants.get(0).getProductId(), 4711l);
        assertTrue(liveVariants.get(0).getAvailableStock() == 5);
        assertTrue(liveVariants.get(0).getPrice() == 1990);
    }

    @Test
    public void testVariantNotFound() {
        ShopApiClient shopApiClient = getNewApiClient(new VariantNotFoundMockClient());

        LiveVariantRequest liveVariantRequest = new LiveVariantRequest.Builder()
                .filterByVariantIds(Arrays.asList(2l))
                .build();

        List<LiveVariant> liveVariants = shopApiClient.requestLiveVariants(liveVariantRequest);

        assertNotNull(liveVariants);
        assertTrue(liveVariants.size() == 1);
        assertTrue(liveVariants.get(0).getErrorCode() == 404);
        assertTrue(liveVariants.get(0).getErrorMessages().size() == 1);
        assertEquals("Variant not found", liveVariants.get(0).getErrorMessages().get(0));
    }

    private class ValidRequestMockClient extends MockClient {

        @Override
        protected void validateRequestBody(String requestBody) {
            assertEquals("[{\"live_variant\":{\"ids\":[1]}}]", requestBody);
        }

        @Override
        protected String getResponse() {
            return "[{\"live_variant\":{\"1\":{\"id\":\"1\",\"product_id\":\"4711\",\"available_stock\":\"5\",\"price\":\"1990\"}}}]";
        }

    }

    private class VariantNotFoundMockClient extends MockClient {

        @Override
        protected String getResponse() {
            return "[{\"live_variant\":{\"2\":{\"error_message\":[\"Variant not found\"],\"error_code\":404,\"id\":2}}}]";
        }
    }
}