package com.flock.frule.configRule.nodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.IdentityNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class IdentityNodeFactory implements NodeFactory<JsonType> {

    static {
        IdentityNodeFactory equalsOperator = new IdentityNodeFactory();
        NodeManager.registerNodeFactory("IDTY",equalsOperator);
    }

    @Override
    public Node<JsonType> getInstance(JsonType json) throws InvalidObjectException, IllegalAccessException {
        return new IdentityNode();
    }
}
