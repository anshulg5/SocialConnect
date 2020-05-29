package com.flock.frule.model;

import com.flock.frule.util.HttpClientWrapper;
import org.eclipse.jetty.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletionStage;

public class MakeHttpRequest implements DataOperation<JsonData>{
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final HttpMethod method;
    private final Node<String> url;
    private final DataOperation<JsonData> postData;

    private final HttpClientWrapper httpClient;

    public MakeHttpRequest(HttpMethod method, Node<String> url, ApplyOnInput postData, HttpClientWrapper httpClient) {
        this.method = method;
        this.url = url;
        this.postData = postData;
        this.httpClient = httpClient;
    }

    //TODO: handle GET request
    @Override
    public JsonData execute(JsonData input) {
        String endpoint = url.apply(input);
        JsonData filledData = postData.execute(input);

        CompletionStage<Void> stage = httpClient.execute(method, endpoint, filledData);

        return stage.toCompletableFuture().thenApply(__ -> {
            log.debug("{} request completed. \n data used: {}",method,filledData);
            JsonData result = JsonData.createEmpty();
            result.put("result","request completed");
            return result;
        }).join();
    }
}
