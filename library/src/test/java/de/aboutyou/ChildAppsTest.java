package de.aboutyou;

import org.junit.Test;

import java.util.List;

import de.aboutyou.models.ChildApp;
import de.aboutyou.request.ChildAppsRequest;
import de.aboutyou.util.MockClient;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ChildAppsTest extends TestBase {

    @Test
    public void testValidRequest() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidRequestMockClient());
        shopApiClient.requestChildApps();
    }

    private class ValidRequestMockClient extends MockClient {

        @Override
        protected void validateRequestBody(String requestBody) {
            assertEquals("[{\"child_apps\":{}}]", requestBody);
        }

        @Override
        protected String getResponse() {
            return "[{\"child_apps\":[]}]";
        }

    }

    @Test
    public void testValidResponse() {
        ShopApiClient shopApiClient = getNewApiClient(new ValidResponseMockClient());

        List<ChildApp> childApps = shopApiClient.requestChildApps();

        assertNotNull(childApps);
        assertTrue(childApps.size() == 1);
        assertEquals(1, childApps.get(0).getId());
        assertEquals("AboutYou", childApps.get(0).getName());
        assertEquals("http://www.aboutyou.de", childApps.get(0).getUrl());
        assertEquals("http://www.aboutyou.de/logo.png", childApps.get(0).getLogoUrl());
        assertEquals("http://www.aboutyou.de/tos", childApps.get(0).getTosUrl());
        assertEquals("http://www.aboutyou.de/privacy", childApps.get(0).getPrivacyStatementUrl());
    }

    private class ValidResponseMockClient extends MockClient {

        @Override
        protected String getResponse() {
            return "[{\"child_apps\":{\"1\":{\"id\":1,\"name\":\"AboutYou\",\"url\":\"http://www.aboutyou.de\",\"logo_url\":\"http://www.aboutyou.de/logo.png\",\"tos_url\":\"http://www.aboutyou.de/tos\",\"privacy_statement_url\":\"http://www.aboutyou.de/privacy\"}}}]";
        }

    }
}