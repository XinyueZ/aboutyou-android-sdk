package de.aboutyou.enums;

public enum Endpoint {

    LIVE("http://ant-shop-api1.wavecloud.de", "checkout.aboutyou.de", "m-checkout.aboutyou.de"),
    STAGE("http://ant-core-staging-s-api1.wavecloud.de", "checkout.aboutyou.de", "m-checkout.aboutyou.de");

    private String url;
    private String authAuthority;
    private String authAuthorityMobile;

    private Endpoint(String url, String authAuthority, String authAuthorityMobile) {
        this.url = url;
        this.authAuthority = authAuthority;
        this.authAuthorityMobile = authAuthorityMobile;
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
}
