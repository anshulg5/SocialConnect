package com.flock.frule.configRule.nodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.EqualsNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;

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
