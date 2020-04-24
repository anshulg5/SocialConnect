package org.configRule.NodeFactory.primitiveNodeFactory;

import org.configRule.Node.primitiveNode.IntegerNode;
import org.example.Node;
import org.example.NodeFactory;
import org.example.NodeManager;

import java.util.Map;

public class IntegerNodeFactory implements NodeFactory {
    static {
        IntegerNodeFactory integerNodeOperator = new IntegerNodeFactory();
        NodeManager.registerNodeFactory("INT",integerNodeOperator);
    }

    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) {
        return new IntegerNode((Integer)value);
    }

    @Override
    public Node getInstance(Object value) {
        return new IntegerNode((Integer) value);
    }
}
