package com.flock.frule.configRule.nodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.AndNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;

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
