package de.aboutyou.request;

import com.google.gson.annotations.SerializedName;

public class ChildAppsRequest extends CollinsRequest {

    @SerializedName("child_apps")
    private Object childApps = new Object();

}