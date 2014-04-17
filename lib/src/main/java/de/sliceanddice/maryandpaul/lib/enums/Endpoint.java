package de.sliceanddice.maryandpaul.lib.enums;

public enum Endpoint {

    LIVE("http://ant-shop-api1.wavecloud.de", "checkout.mary-paul.de"),
    STAGE("http://ant-core-staging-s-api1.wavecloud.de", "checkout.mary-paul.de"); // TODO proper auth authority

    private String url;
    private String authAuthority;

    private Endpoint(String url, String authAuthority) {
        this.url = url;
        this.authAuthority = authAuthority;
    }

    public String getUrl() {
        return url;
    }

    public String getAuthAuthority() {
        return authAuthority;
    }
}
