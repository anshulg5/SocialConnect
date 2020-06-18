package com.flock.frule.configRule.nodefactories.jsonnodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.jsonnodes.JsonPrimitiveNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonPrimitive;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class JsonPrimitiveNodeFactory implements NodeFactory<JsonPrimitive> {
    static {
        JsonPrimitiveNodeFactory jsonPrimitiveOperator = new JsonPrimitiveNodeFactory();
        NodeManager.registerNodeFactory("JSONPrimitive",jsonPrimitiveOperator);
    }

    @Override
    public Node<JsonPrimitive> getInstance(JsonType json) throws InvalidObjectException {
        return new JsonPrimitiveNode(json);
    }
}
