package org.configRule.NodeFactory;

import org.configRule.Node.AndNode;
import org.example.Node;
import org.example.NodeFactory;
import org.example.NodeManager;

import java.util.List;
import java.util.Map;

public class AndNodeFactory implements NodeFactory {

    static {
        AndNodeFactory andOperator = new AndNodeFactory();
        NodeManager.registerNodeFactory("AND",andOperator);
    }

    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) throws IllegalAccessException {
        return new AndNode((List<Map<String, Object>>) value,symbolTable);
    }

    @Override
    public Node getInstance(Object value) throws IllegalAccessException {
        return new AndNode((List<Map<String, Object>>) value);
    }
}
