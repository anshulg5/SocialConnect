package com.flock.frule.configRule.nodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.EqualsNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class EqualsNodeFactory implements NodeFactory {

    static {
        EqualsNodeFactory equalsOperator = new EqualsNodeFactory();
        NodeManager.registerNodeFactory("EQ",equalsOperator);
    }

    @Override
    public Node getInstance(JsonType json) throws InvalidObjectException, IllegalAccessException {
        return new EqualsNode(json.asObject());
    }
}
