package de.sliceanddice.maryandpaul.lib.models;

import com.google.gson.annotations.SerializedName;

public class InitiateOrder {

    private String url;
    @SerializedName("user_token")
    private String userToken;
    @SerializedName("app_token")
    private String appToken;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getAppToken() {
        return appToken;
    }

    public void setAppToken(String appToken) {
        this.appToken = appToken;
    }
}
