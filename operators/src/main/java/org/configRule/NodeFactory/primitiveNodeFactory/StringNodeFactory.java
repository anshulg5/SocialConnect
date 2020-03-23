package org.configRule.NodeFactory.primitiveNodeFactory;

import org.configRule.Node.primitiveNode.StringNode;
import org.example.Node;
import org.example.NodeFactory;
import org.example.NodeManager;

import java.util.Map;

public class StringNodeFactory implements NodeFactory {
    static {
        StringNodeFactory stringNodeOperator = new StringNodeFactory();
        NodeManager.registerNodeFactory("STR",stringNodeOperator);
    }
    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) {
        return new StringNode((String) value);
    }

    @Override
    public Node getInstance(Object value) {
        return new StringNode((String) value);
    }
}
