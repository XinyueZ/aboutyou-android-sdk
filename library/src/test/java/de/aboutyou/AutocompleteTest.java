package de.aboutyou;

import org.junit.Test;

import de.aboutyou.models.Autocomplete;
import de.aboutyou.request.AutocompleteRequest;
import de.aboutyou.util.MockClient;

import static org.junit.Assert.*;

public class AutocompleteTest extends TestBase {

    @Test
    public void testSuccess() {
        ShopApiClient shopApiClient = getNewApiClient(new SuccessMockClient());

        AutocompleteRequest autocompleteRequest = new AutocompleteRequest.Builder("to").build();

        Autocomplete autocomplete = shopApiClient.requestAutocompletion(autocompleteRequest);

        assertNotNull(autocomplete);
        assertTrue(autocomplete.getProducts().size() == 1);
        assertTrue(autocomplete.getCategories().size() == 1);
    }

    @Test
    public void testMissingSearchword() {
        ShopApiClient shopApiClient = getNewApiClient(new MissingSearchwordMockClient());

        AutocompleteRequest autocompleteRequest = new AutocompleteRequest.Builder(null).build();

        Autocomplete autocomplete = shopApiClient.requestAutocompletion(autocompleteRequest);

        assertNotNull(autocomplete);
        assertTrue(autocomplete.getProducts().size() == 0);
        assertTrue(autocomplete.getCategories().size() == 0);
        assertTrue(autocomplete.getErrorCode() == 400);
        assertTrue(autocomplete.getErrorMessages().size() == 1);
        assertEquals(": 'searchword' is required property", autocomplete.getErrorMessages().get(0));
    }

    @Test
    public void testShortSearchword() {
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

    private class SuccessMockClient extends MockClient {

        @Override
        protected String getResponse() {
            return "[{\"autocompletion\":{\"products\":[{\"name\":\"Top\",\"id\":1}],\"categories\":[{\"name\":\"Tops\",\"id\":1}]}}]";
        }

    }

    private class MissingSearchwordMockClient extends MockClient {

        @Override
        protected String getResponse() {
            return "[{\"autocompletion\":{\"error_message\":[\": 'searchword' is required property\"],\"error_code\":400}}]";
        }

    }

    private class ShortSearchwordMockClient extends MockClient {

        @Override
        protected String getResponse() {
            return "[{\"autocompletion\":{\"error_message\":[\"searchword: u't' is too short\"],\"error_code\":400}}]";
        }

    }
}



