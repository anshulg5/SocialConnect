package com.flock.frule.configRule.nodefactories.jsonnodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.jsonnodes.JsonObjectNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class JsonObjectNodeFactory implements NodeFactory<JsonObject> {
    static {
        JsonObjectNodeFactory jsonObjectOperator = new JsonObjectNodeFactory();
        NodeManager.registerNodeFactory("JSONObj",jsonObjectOperator);
    }

    @Override
    public Node<JsonObject> getInstance(JsonType json) throws InvalidObjectException {
        return new JsonObjectNode(json);
    }
}
