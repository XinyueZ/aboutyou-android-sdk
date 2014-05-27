package de.aboutyou.enums;

/** OAuth scopes */
public enum AuthScope {

    FIRSTNAME("firstname"), LASTNAME("lastname"), ID("id"), EMAIL("email");

    private String scope;

    private AuthScope(String scope) {
        this.scope = scope;
    }

    public String getScope() {
        return scope;
    }
}
