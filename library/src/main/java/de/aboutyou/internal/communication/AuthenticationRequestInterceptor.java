package de.aboutyou.internal.communication;

import com.squareup.okhttp.OkAuthenticator;

import retrofit.RequestInterceptor;

public class AuthenticationRequestInterceptor implements RequestInterceptor {

    private static final String HEADER_AUTHORIZATION = "Authorization";

    private final String mAppId;
    private final String mAppPassword;

    public AuthenticationRequestInterceptor(String appId, String appPassword) {
        mAppId = appId;
        mAppPassword = appPassword;
    }

    @Override
    public void intercept(RequestFacade requestFacade) {
        OkAuthenticator.Credential credential = OkAuthenticator.Credential.basic(mAppId, mAppPassword);
        requestFacade.addHeader(HEADER_AUTHORIZATION, credential.getHeaderValue());
    }
}
