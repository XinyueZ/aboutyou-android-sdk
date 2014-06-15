package de.aboutyou.internal.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.aboutyou.models.ChildApp;

public class ChildAppsResponse extends CollinsResponse<List<ChildApp>> {

    @SerializedName("child_apps")
    private Map<Long, ChildApp> childApps;

    @Override
    public List<ChildApp> get() {
        return new ArrayList<>(childApps.values());
    }
}
