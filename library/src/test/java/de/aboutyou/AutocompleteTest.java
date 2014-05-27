package de.aboutyou;

import org.junit.Test;

import de.aboutyou.models.Autocomplete;
import de.aboutyou.request.AutocompleteRequest;
import de.aboutyou.util.MockClient;

import static org.junit.Assert.*;

public class AutocompleteTest extends TestBase {

    @Test
    public void testValidRequest() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidRequestMockClient());

        AutocompleteRequest autocompleteRequest = new AutocompleteRequest.Builder("to").build();
        shopApiClient.requestAutocompletion(autocompleteRequest);
    }

    private class ValidRequestMockClient extends MockClient {

        @Override
        protected void validateRequestBody(String requestBody) {
            assertEquals("[{\"autocompletion\":{\"searchword\":\"to\"}}]", requestBody);
        }

        @Override
        protected String getResponse() {
            return "[{\"autocompletion\":{}}]";
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMissingSearchwordRequest() {
        new AutocompleteRequest.Builder(null).build();
    }

    @Test
    public void testValidResponse() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidResponseMockClient());

        AutocompleteRequest autocompleteRequest = new AutocompleteRequest.Builder("foobar").build();
        Autocomplete autocomplete = shopApiClient.requestAutocompletion(autocompleteRequest);

        assertNotNull(autocomplete);
        assertTrue(autocomplete.getProducts().size() == 1);
        assertTrue(autocomplete.getCategories().size() == 1);
    }

    private class ValidResponseMockClient extends MockClient {

        @Override
        protected String getResponse() {
            return "[{\"autocompletion\":{\"products\":[{\"name\":\"Top\",\"id\":1}],\"categories\":[{\"name\":\"Tops\",\"id\":1}]}}]";
        }

    }

    @Test
    public void testShortSearchwordResponse() {
        ShopApiClient shopApiClient = getNewApiClient(new ShortSearchwordMockClient());

        AutocompleteRequest autocompleteRequest = new AutocompleteRequest.Builder("t").build();

        Autocomplete autocomplete = shopApiClient.requestAutocompletion(autocompleteRequest);

        assertNotNull(autocomplete);
        assertTrue(autocomplete.getProducts().size() == 0);
        assertTrue(autocomplete.getCategories().size() == 0);
        assertTrue(autocomplete.getErrorCode() == 400);
        assertTrue(autocomplete.getErrorMessages().size() == 1);
        assertEquals("searchword: u't' is too short", autocomplete.getErrorMessages().get(0));
    }

    private class ShortSearchwordMockClient extends MockClient {

        @Override
        protected String getResponse() {
            return "[{\"autocompletion\":{\"error_message\":[\"searchword: u't' is too short\"],\"error_code\":400}}]";
        }

    }

}



