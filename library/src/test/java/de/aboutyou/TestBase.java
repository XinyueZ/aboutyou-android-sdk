package de.aboutyou;

import de.aboutyou.enums.Endpoint;
import retrofit.client.Client;

public abstract class TestBase {

    private static final String APP_ID = "";
    private static final String APP_PASSWORD = "";

    protected ShopApiClient getApiClient(Client retrofitClient) {
        ShopApiClient.Logger logger = new ShopApiClient.Logger() {
            @Override
            public void log(String message) {
            }
        };

        return new ShopApiClient(APP_ID, APP_PASSWORD, Endpoint.STAGE, logger, retrofitClient);
    }
}
