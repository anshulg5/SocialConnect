package com.flock.frule.configRule.nodefactories.jsonnodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.jsonnodes.JsonNullNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonNull;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class JsonNullNodeFactory implements NodeFactory<JsonNull> {
    static {
        JsonNullNodeFactory jsonNullOperator = new JsonNullNodeFactory();
        NodeManager.registerNodeFactory("JSONNull",jsonNullOperator);
    }

    @Override
    public Node<JsonNull> getInstance(JsonType json) throws InvalidObjectException {
        return new JsonNullNode(json);
    }
}