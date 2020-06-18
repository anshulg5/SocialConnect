package com.flock.frule.configRule.nodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.OrNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class OrNodeFactory implements NodeFactory<Boolean> {

    static {
        OrNodeFactory andOperator = new OrNodeFactory();
        NodeManager.registerNodeFactory("OR",andOperator);
    }

    @Override
    public Node<Boolean> getInstance(JsonType json) throws InvalidObjectException, IllegalAccessException {
        return new OrNode(json.asObject());
    }
}
