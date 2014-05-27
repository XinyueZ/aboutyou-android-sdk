package de.aboutyou.internal.communication;

import de.aboutyou.models.ShopUser;
import retrofit.http.GET;
import retrofit.http.Header;

public interface MeInterface {

    static final String BASEPATH = "/oauth/api";

    @GET(BASEPATH + "/me")
    ShopUser requestUser(@Header("Authorization") String token);

}
