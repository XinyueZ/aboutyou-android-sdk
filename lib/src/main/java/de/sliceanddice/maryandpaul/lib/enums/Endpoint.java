package de.sliceanddice.maryandpaul.lib.enums;

public enum Endpoint {

    LIVE("http://ant-shop-api1.wavecloud.de"),
    STAGE("http://ant-core-staging-s-api1.wavecloud.de");

    private String url;

    private Endpoint(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
