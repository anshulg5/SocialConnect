package com.flock.frule.configRule.nodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.LessThanNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class LessThanNodeFactory implements NodeFactory<Boolean> {

    static {
        LessThanNodeFactory lessThanOperator = new LessThanNodeFactory();
        NodeManager.registerNodeFactory("LESS",lessThanOperator);
    }

    @Override
    public Node<Boolean> getInstance(JsonType json) throws InvalidObjectException, IllegalAccessException {
        return new LessThanNode(json.asObject());
    }
}
