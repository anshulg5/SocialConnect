package com.flock.frule.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class HttpClientHelper {
    private static HttpClient httpClient = HttpClientBuilder.create().build();
    private static ObjectMapper mapper = new ObjectMapper();

    public static HttpResponse POST(String url, Object requestBody) throws IOException {
        HttpPost request = new HttpPost(url);
        String contentRequest = mapper.writeValueAsString(requestBody);
        request.setHeader("Content-type", "application/json");
        request.setEntity(new StringEntity(contentRequest));

        return httpClient.execute(request);
    }
}
