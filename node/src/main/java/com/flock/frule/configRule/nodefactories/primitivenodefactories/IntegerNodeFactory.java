package com.flock.frule.configRule.nodefactories.primitivenodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.primitivenodes.IntegerNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;

import java.util.Map;

public class IntegerNodeFactory implements NodeFactory {
    static {
        IntegerNodeFactory integerNodeOperator = new IntegerNodeFactory();
        NodeManager.registerNodeFactory("INT",integerNodeOperator);
    }

    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) {
        return new IntegerNode(value);
    }

    @Override
    public Node getInstance(Object value) {
        return new IntegerNode(value);
    }
}
