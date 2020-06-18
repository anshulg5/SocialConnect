package com.flock.frule.configRule.nodefactories.primitivenodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.primitivenodes.IntegerNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class IntegerNodeFactory implements NodeFactory<Integer> {
    static {
        IntegerNodeFactory integerNodeOperator = new IntegerNodeFactory();
        NodeManager.registerNodeFactory("INT", integerNodeOperator);
    }

    @Override
    public Node<Integer> getInstance(JsonType json) throws InvalidObjectException, IllegalAccessException {
        return new IntegerNode(json.asObject());
    }
}