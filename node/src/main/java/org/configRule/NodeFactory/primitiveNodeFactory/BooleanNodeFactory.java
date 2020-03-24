package org.configRule.NodeFactory.primitiveNodeFactory;

import org.configRule.Node.primitiveNode.BooleanNode;

import org.example.Node;
import org.example.NodeFactory;
import org.example.NodeManager;

import java.util.Map;

public class BooleanNodeFactory implements NodeFactory {
    static {
        BooleanNodeFactory booleanNodeOperator = new BooleanNodeFactory();
        NodeManager.registerNodeFactory("BOOL",booleanNodeOperator);
    }

    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) throws IllegalAccessException {
        return new BooleanNode((Boolean)value);
    }

    @Override
    public Node getInstance(Object value) throws IllegalAccessException {
        return new BooleanNode((Boolean) value);
    }
}
