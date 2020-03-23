package org.configRule.NodeFactory;

import org.configRule.Node.PathNode;
import org.example.Node;
import org.example.NodeFactory;
import org.example.NodeManager;

import java.util.Map;

public class PathNodeFactory implements NodeFactory {
    static {
        PathNodeFactory pathOperator = new PathNodeFactory();
        NodeManager.registerNodeFactory("PTH",pathOperator);
    }
    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) throws IllegalAccessException {
        return new PathNode((Map<NodeFactory, Object>) value,symbolTable);
    }

    @Override
    public Node getInstance(Object value) throws IllegalAccessException {
        return new PathNode((Map<NodeFactory, Object>) value);
    }
}
