package org.configRule.Node.vars;

import com.flock.frule.configRule.util.HttpClientWrapper;
import com.flock.frule.model.JsonData;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.eclipse.jetty.http.HttpMethod;
import org.example.Node;

import java.io.IOException;
import java.util.concurrent.CompletionException;
import java.util.concurrent.CompletionStage;

public class VarGetRequest implements Node<JsonData> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private final VarJsonData arg;
    private final HttpClientWrapper httpClient;

    public VarGetRequest(VarJsonData arg, HttpClientWrapper httpClient) {
        this.arg = arg;
        this.httpClient = httpClient;
    }


    @Override
    public JsonData apply(JsonData input) {
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
