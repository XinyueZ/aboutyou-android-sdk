package de.aboutyou.enums;

/** SDK endpoint */
public enum Endpoint {

    LIVE("http://ant-shop-api1.wavecloud.de", "checkout.aboutyou.de", "m-checkout.aboutyou.de", "https://oauth.collins.kg"),
    STAGE("http://ant-core-staging-s-api1.wavecloud.de", "checkout.aboutyou.de", "m-checkout.aboutyou.de", "https://oauth.collins.kg");

    private String url;
    private String authAuthority;
    private String authAuthorityMobile;
    private String meUrl;

    private Endpoint(String url, String authAuthority, String authAuthorityMobile, String meUrl) {
        this.url = url;
        this.authAuthority = authAuthority;
        this.authAuthorityMobile = authAuthorityMobile;
        this.meUrl = meUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthAuthority() {
        return authAuthority;
    }

    public String getAuthAuthorityMobile() {
        return authAuthorityMobile;
    }

    public String getMeUrl() {
        return meUrl;
    }
}
