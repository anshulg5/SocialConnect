package com.flock.frule.configRule.nodefactories;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.PathNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;

import java.util.Map;

public class PathNodeFactory implements NodeFactory {
    static {
        PathNodeFactory pathOperator = new PathNodeFactory();
        NodeManager.registerNodeFactory("PTH",pathOperator);
    }
    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) throws IllegalAccessException {
        return new PathNode((Map<String, Object>) value,symbolTable);
    }

    @Override
    public Node getInstance(Object value) throws IllegalAccessException {
        return new PathNode((Map<String, Object>) value);
    }
}
