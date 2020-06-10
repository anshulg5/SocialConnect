package com.flock.frule.configRule.nodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.PathNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class PathNodeFactory implements NodeFactory {
    static {
        PathNodeFactory pathOperator = new PathNodeFactory();
        NodeManager.registerNodeFactory("PTH",pathOperator);
    }

    @Override
    public Node getInstance(JsonType json) throws InvalidObjectException, IllegalAccessException {
        return new PathNode(json.asObject());
    }
}
