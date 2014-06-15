package de.aboutyou.models;

import com.google.gson.annotations.SerializedName;

public class ChildApp {

    private long id;
    private String name;
    private String url;
    @SerializedName("logo_url")
    private String logoUrl;
    @SerializedName("privacy_statement_url")
    private String privacyStatementUrl;
    @SerializedName("tos_url")
    private String tosUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getPrivacyStatementUrl() {
        return privacyStatementUrl;
    }

    public void setPrivacyStatementUrl(String privacyStatementUrl) {
        this.privacyStatementUrl = privacyStatementUrl;
    }

    public String getTosUrl() {
        return tosUrl;
    }

    public void setTosUrl(String tosUrl) {
        this.tosUrl = tosUrl;
    }
}
