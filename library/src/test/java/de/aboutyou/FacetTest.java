package de.aboutyou;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.aboutyou.enums.FacetType;
import de.aboutyou.models.Facet;
import de.aboutyou.request.FacetRequest;
import de.aboutyou.util.MockClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FacetTest extends TestBase {

    @Test
    public void testValidRequest() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidRequestMockClient());

        Map<Long, FacetType> facetMap = new HashMap<>();
        facetMap.put(1l, FacetType.COLOR);

        FacetRequest facetRequest = new FacetRequest.Builder()
                .selectFacets(facetMap)
                .build();
        shopApiClient.requestFacet(facetRequest);
    }

    private class ValidRequestMockClient extends MockClient {

        @Override
        protected void validateRequestBody(String requestBody) {
            assertEquals("[{\"facet\":[{\"id\":1,\"group_id\":1}]}]", requestBody);
        }

        @Override
        protected String getResponse() {
            return "[{\"facet\":[]}]";
        }

    }

    @Test
    public void testValidResponse() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidResponseMockClient());

        Map<Long, FacetType> facetMap = new HashMap<>();
        facetMap.put(1l, FacetType.COLOR);

        FacetRequest facetRequest = new FacetRequest.Builder()
                .selectFacets(facetMap)
                .build();

        List<Facet> facets = shopApiClient.requestFacet(facetRequest);

        assertNotNull(facets);
        assertTrue(facets.size() == 1);
        assertEquals("Blau", facets.get(0).getName());
    }

    private class ValidResponseMockClient extends MockClient {

        @Override
        protected String getResponse() {
            return "[{\"facet\":[{\"name\":\"Blau\",\"facet_id\":1,\"id\":1,\"value\":\"blau\",\"group_name\":\"color\"}]}]";
        }

    }
}