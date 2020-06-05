package com.flock.frule.configRule.nodes.vars;

import com.flock.frule.configRule.util.HttpClientWrapper;
import com.flock.frule.model.JsonData;
import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.eclipse.jetty.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;

public class VarGetRequest implements Node<JsonObject> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final VarJsonObjectNode arg;
    private final HttpClientWrapper httpClient;

    public VarGetRequest(VarJsonObjectNode arg, HttpClientWrapper httpClient) {
        this.arg = arg;
        this.httpClient = httpClient;
    }


    @Override
    public JsonObject apply(JsonData input) {
        JsonData filledJsonData = arg.apply(input);
        String url = filledJsonData.get("url",String.class);

        CompletionStage<HttpResponse> stage = httpClient.execute(HttpMethod.GET, url, null);

        return stage.toCompletableFuture().thenApply(resp -> {
            try {
                HttpEntity entity = resp.getEntity();
                String encoding = entity.getContentEncoding() == null ? "UTF-8" : entity.getContentEncoding().toString();
                String responseString = EntityUtils.toString(entity, encoding);
                log.debug("GET request completed \n data fetched {}.",responseString);
                return JsonData.fromJson(responseString);
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        }).join();
    }
}
