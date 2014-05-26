package de.aboutyou;

import org.junit.Test;

import de.aboutyou.models.Autocomplete;
import de.aboutyou.models.Suggest;
import de.aboutyou.request.AutocompleteRequest;
import de.aboutyou.request.SuggestRequest;
import de.aboutyou.util.MockClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SuggestTest extends TestBase {

    @Test
    public void testValidRequest() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidRequestMockClient());

        SuggestRequest suggestRequest = new SuggestRequest.Builder("Schuh")
                .build();

        Suggest suggest = shopApiClient.requestSuggest(suggestRequest);

        assertNotNull(suggest);
        assertTrue(suggest.size() == 2);
        assertEquals("bar", suggest.get(1));
    }

    private class ValidRequestMockClient extends MockClient {

        @Override
        protected void validateRequestBody(String requestBody) {
            assertEquals("[{\"suggest\":{\"searchword\":\"Schuh\"}}]", requestBody);
        }

        @Override
        protected String getResponse() {
            return "[{\"suggest\":[\"foo\",\"bar\"]}]";
        }

    }

}



