package com.flock.frule.configRule.nodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.GreaterThanNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class GreaterThanNodeFactory implements NodeFactory<Boolean> {

    static {
        GreaterThanNodeFactory equalsOperator = new GreaterThanNodeFactory();
        NodeManager.registerNodeFactory("GTR",equalsOperator);
    }

    @Override
    public Node<Boolean> getInstance(JsonType json) throws InvalidObjectException, IllegalAccessException {
        return new GreaterThanNode(json.asObject());
    }
}
