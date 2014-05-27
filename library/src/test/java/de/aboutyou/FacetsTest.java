package de.aboutyou;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.aboutyou.enums.FacetType;
import de.aboutyou.models.Facet;
import de.aboutyou.request.FacetsRequest;
import de.aboutyou.util.MockClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FacetsTest extends TestBase {

    @Test
    public void testValidRequest() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidRequestMockClient());

        FacetsRequest facetsRequest = new FacetsRequest.Builder()
                .filterByFacetTypes(Arrays.asList(FacetType.SIZE))
                .build();
        shopApiClient.requestFacets(facetsRequest);
    }

    private class ValidRequestMockClient extends MockClient {

        @Override
        protected void validateRequestBody(String requestBody) {
            assertEquals("[{\"facets\":{\"group_ids\":[2]}}]", requestBody);
        }

        @Override
        protected String getResponse() {
            return "[{\"facets\":{}}]";
        }

    }

    @Test
    public void testValidResponse() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidResponseMockClient());

        FacetsRequest facetsRequest = new FacetsRequest.Builder()
                .filterByFacetTypes(Collections.<FacetType>emptyList())
                .build();

        List<Facet> facets = shopApiClient.requestFacets(facetsRequest);

        assertNotNull(facets);
        assertTrue(facets.size() == 1);
        assertEquals("Rot", facets.get(0).getName());
    }

    private class ValidResponseMockClient extends MockClient {

        @Override
        protected String getResponse() {
            return "[{\"facets\":{\"facet\":[{\"name\":\"Rot\",\"facet_id\":4711,\"id\":1,\"value\":rot,\"group_name\":\"color\"}],\"hits\":2}}]";
        }

    }
}