package de.sliceanddice.maryandpaul.lib.internal.communication;

import com.squareup.okhttp.OkAuthenticator;

import retrofit.RequestInterceptor;

public class AuthenticationRequestInterceptor implements RequestInterceptor {

    private final String mAppId;
    private final String mAppPassword;

    public AuthenticationRequestInterceptor(String appId, String appPassword) {
        mAppId = appId;
        mAppPassword = appPassword;
    }

    @Override
    public void intercept(RequestFacade requestFacade) {
        OkAuthenticator.Credential credential = OkAuthenticator.Credential.basic(mAppId, mAppPassword);
        requestFacade.addHeader("Authorization", credential.getHeaderValue());
    }
}
