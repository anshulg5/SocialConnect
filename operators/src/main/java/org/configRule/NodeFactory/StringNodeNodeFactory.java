package org.configRule.NodeFactory;

import org.configRule.Node.primitiveNode.StringNode;
import org.example.Node;
import org.example.NodeFactory;
import org.example.NodeManager;

import java.util.Map;

public class StringNodeNodeFactory implements NodeFactory {
    static {
        StringNodeNodeFactory stringNodeOperator = new StringNodeNodeFactory();
        NodeManager.registerNodeFactory("Str",stringNodeOperator);
    }
    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) {
        return new StringNode((String) value);
    }
}
