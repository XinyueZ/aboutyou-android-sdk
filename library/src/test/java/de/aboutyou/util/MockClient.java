package de.aboutyou.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collections;

import retrofit.client.Client;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedOutput;

public abstract class MockClient implements Client {

    @Override
    public Response execute(Request request) throws IOException {
        validateRequest(request);
        return new Response(request.getUrl(), 200, "no reason", Collections.EMPTY_LIST, new TypedByteArray("application/json", getResponse().getBytes()));
    }

    private void validateRequest(Request request) {
        TypedOutput body = request.getBody();
        if (body.length() == 0) {
            return;
        }

        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            request.getBody(). writeTo(baos);
            String requestBody = new String(baos.toByteArray(), "UTF-8");
            validateRequestBody(requestBody);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void validateRequestBody(String requestBody){
        // default implementation: do nothing
    }

    protected abstract String getResponse();
}
