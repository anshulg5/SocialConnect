package com.flock.frule.configRule.nodefactories.targetactionfactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.targetaction.GenericWebhook;
import com.flock.frule.configRule.util.HttpClientWrapper;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;
import java.util.concurrent.CompletionStage;

public class GenericWebhookFactory implements NodeFactory<CompletionStage<Void>> {

    private static final HttpClientWrapper httpClientWrapper;

    static {
        GenericWebhookFactory genericWebhookOperator = new GenericWebhookFactory();
        NodeManager.registerNodeFactory("GenWHook",genericWebhookOperator);
        httpClientWrapper = new HttpClientWrapper();
    }

    @Override
    public Node<CompletionStage<Void>> getInstance(JsonType json) throws InvalidObjectException, IllegalAccessException {
        return new GenericWebhook(json.asObject(), httpClientWrapper);
    }
}
