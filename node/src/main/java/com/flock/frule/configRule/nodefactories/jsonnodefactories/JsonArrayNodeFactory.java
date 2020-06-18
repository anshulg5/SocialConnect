package com.flock.frule.configRule.nodefactories.jsonnodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.jsonnodes.JsonArrayNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonArray;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class JsonArrayNodeFactory implements NodeFactory<JsonArray> {
    static {
        JsonArrayNodeFactory jsonArrayOperator = new JsonArrayNodeFactory();
        NodeManager.registerNodeFactory("JSONArr",jsonArrayOperator);
    }

    @Override
    public Node<JsonArray> getInstance(JsonType json) throws InvalidObjectException {
        return new JsonArrayNode(json);
    }
}
