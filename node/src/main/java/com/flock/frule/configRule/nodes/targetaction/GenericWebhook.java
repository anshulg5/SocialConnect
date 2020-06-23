package com.flock.frule.configRule.nodes.targetaction;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.util.HttpClientWrapper;
import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonType;
import org.eclipse.jetty.http.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InvalidObjectException;
import java.util.concurrent.CompletionStage;

public class GenericWebhook implements TargetAction {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    public static final String TYPE = "GenWHook";
    private final Node<String> urlNode;
    private final HttpClientWrapper httpClientWrapper;

    public GenericWebhook(JsonObject json, HttpClientWrapper httpClientWrapper) throws InvalidObjectException, IllegalAccessException {
        JsonType arg = json.get(TYPE).asObject().get("url");
        urlNode = NodeManager.create(arg);
        this.httpClientWrapper = httpClientWrapper;
    }


    @Override
    public CompletionStage<Void> apply(JsonType input) {
        String url = urlNode.apply(input);
        return httpClientWrapper.execute(HttpMethod.POST,url,input);
    }
}
