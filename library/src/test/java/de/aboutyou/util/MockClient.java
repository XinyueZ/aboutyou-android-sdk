package de.aboutyou.util;

import java.io.IOException;
import java.util.Collections;

import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;

public abstract class MockClient implements Client {

    @Override
    public Response execute(Request request) throws IOException {
        return new Response(request.getUrl(), 200, "no reason", Collections.EMPTY_LIST, new TypedByteArray("application/json", getResponse().getBytes()));
    }

    protected abstract String getResponse();
}
