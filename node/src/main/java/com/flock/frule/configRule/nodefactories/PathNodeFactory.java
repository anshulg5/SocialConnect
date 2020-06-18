package com.flock.frule.configRule.nodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.PathNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonArray;
import com.flock.frule.model.jsondata.JsonPrimitive;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class PathNodeFactory<T> implements NodeFactory<T> {
    static {
        PathNodeFactory<JsonPrimitive> pathOperator = new PathNodeFactory<>();
        NodeManager.registerNodeFactory("PTH", pathOperator);

        PathNodeFactory<JsonArray> innerPathOperator = new PathNodeFactory<>();
        NodeManager.registerNodeFactory("IPTH", innerPathOperator);
    }

    @Override
    public Node<T> getInstance(JsonType json) throws InvalidObjectException, IllegalAccessException {
        return new PathNode<>(json.asObject());
    }
}
