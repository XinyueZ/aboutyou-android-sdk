package de.aboutyou;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import de.aboutyou.enums.FacetGroup;
import de.aboutyou.models.Category;
import de.aboutyou.models.Facet;
import de.aboutyou.request.CategoriesRequest;
import de.aboutyou.request.FacetsRequest;
import de.aboutyou.util.MockClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class FacetsTest extends TestBase {

    @Test
    public void testSuccess() {
        ShopApiClient shopApiClient = getNewApiClient(new SuccessMockClient());

        FacetsRequest facetsRequest = new FacetsRequest.Builder()
                .filterByFacetGroup(Arrays.asList(FacetGroup.SIZE))
                .build();

        List<Facet> facets = shopApiClient.requestFacets(facetsRequest);

        assertNotNull(facets);
        assertTrue(facets.size() == 1);
        assertEquals(facets.get(0).getName(), "Rot");
    }

    private class SuccessMockClient extends MockClient {

        @Override
        protected void validateRequestBody(String requestBody) {
            assertEquals(requestBody, "[{\"facets\":{\"group_ids\":[2]}}]");
        }

        @Override
        protected String getResponse() {
            return "[{\"facets\":{\"facet\":[{\"name\":\"Rot\",\"facet_id\":4711,\"id\":1,\"value\":rot,\"group_name\":\"color\"}],\"hits\":2}}]";
        }

    }
}