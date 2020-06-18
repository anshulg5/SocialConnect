package com.flock.frule.configRule.nodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.AndNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class AndNodeFactory implements NodeFactory<Boolean> {

    static {
        AndNodeFactory andOperator = new AndNodeFactory();
        NodeManager.registerNodeFactory("AND",andOperator);
    }

    @Override
    public Node<Boolean> getInstance(JsonType json) throws InvalidObjectException, IllegalAccessException {
        return new AndNode(json.asObject());
    }
}
