package de.aboutyou;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import de.aboutyou.enums.FacetGroup;
import de.aboutyou.models.Facet;
import de.aboutyou.request.FacetsRequest;
import de.aboutyou.util.MockClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FacetTypesTest extends TestBase {

    @Test
    public void testValidRequest() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidRequestMockClient());

        shopApiClient.requestFacetTypes();
    }

    private class ValidRequestMockClient extends MockClient {

        @Override
        protected void validateRequestBody(String requestBody) {
            assertEquals("[{\"facet_types\":{}}]", requestBody);
        }

        @Override
        protected String getResponse() {
            return "[{\"facet_types\":[]}]";
        }

    }

    @Test
    public void testValidResponse() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidResponseMockClient());

        List<FacetGroup> facetGroups = shopApiClient.requestFacetTypes();

        assertNotNull(facetGroups);
        assertTrue(facetGroups.size() == 5);
        assertEquals(FacetGroup.SIZE, facetGroups.get(2));
    }

    private class ValidResponseMockClient extends MockClient {

        @Override
        protected String getResponse() {
            return "[{\"facet_types\":[0,1,2,3,4]}]";
        }

    }
}