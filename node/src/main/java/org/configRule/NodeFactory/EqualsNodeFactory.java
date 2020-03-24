package org.configRule.NodeFactory;

import org.configRule.Node.EqualsNode;
import org.example.Node;
import org.example.NodeFactory;
import org.example.NodeManager;

import java.util.List;
import java.util.Map;

public class EqualsNodeFactory implements NodeFactory {

    static {
        EqualsNodeFactory equalsOperator = new EqualsNodeFactory();
        NodeManager.registerNodeFactory("EQ",equalsOperator);
    }

    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) throws IllegalAccessException {
        return new EqualsNode((List<Map<String, Object>>) value,symbolTable);
    }

    @Override
    public Node getInstance(Object value) throws IllegalAccessException {
        return new EqualsNode((List<Map<String, Object>>) value);
    }
}
