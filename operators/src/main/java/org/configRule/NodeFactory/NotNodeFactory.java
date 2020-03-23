package org.configRule.NodeFactory;

import org.configRule.Node.AndNode;
import org.configRule.Node.NotNode;
import org.example.Node;
import org.example.NodeFactory;
import org.example.NodeManager;

import java.util.List;
import java.util.Map;

public class NotNodeFactory implements NodeFactory {

    static {
        NotNodeFactory notOperator = new NotNodeFactory();
        NodeManager.registerNodeFactory("NOT",notOperator);
    }

    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) throws IllegalAccessException {
        return new NotNode((List<Map<String, Object>>) value,symbolTable);
    }

    @Override
    public Node getInstance(Object value) throws IllegalAccessException {
        return new NotNode((List<Map<String, Object>>) value);
    }
}
