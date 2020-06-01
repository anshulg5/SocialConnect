package com.flock.frule.configRule.nodes.vars;

import com.flock.frule.configRule.util.HttpClientWrapper;
import com.flock.frule.model.JsonData;
import com.flock.frule.model.Node;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.eclipse.jetty.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;

public class VarPostRequest implements Node<JsonData> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final VarJsonData arg;
    private final HttpClientWrapper httpClient;

    public VarPostRequest(VarJsonData arg, HttpClientWrapper httpClient) {
        this.arg = arg;
        this.httpClient = httpClient;
    }

    @Override
    public JsonData apply(JsonData input) {
        JsonData filledJsonData = arg.apply(input);
        String url = filledJsonData.get("url",String.class);
        Object postData = filledJsonData.get("post_data",Object.class);
        System.out.println("hi");
        log.debug("making POST request at {}, using data: {}",url,postData);

        CompletionStage<HttpResponse> stage = httpClient.execute(HttpMethod.POST, url, postData);


        return stage.toCompletableFuture().thenApply(resp -> {
            try {
                HttpEntity entity = resp.getEntity();
                String encoding = entity.getContentEncoding() == null ? "UTF-8" : entity.getContentEncoding().toString();
                String responseString = EntityUtils.toString(entity, encoding);
                log.debug("POST request completed \n data received {}.",responseString);
                return JsonData.fromJson(responseString);
            } catch (IOException e) {
                throw new CompletionException(e);
            }
        }).join();
    }
}
