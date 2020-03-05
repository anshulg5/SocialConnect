package org.configRule.Operator;

import org.configRule.Node.PathNode;
import org.example.Node;
import org.example.Operator;
import org.example.OperatorManager;

import java.util.Map;

public class PathOperator implements Operator {
    static {
        PathOperator pathOperator = new PathOperator();
        OperatorManager.registerOperator("Path",pathOperator);
    }
    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) {
        return new PathNode((Map<Operator, Object>) value,symbolTable);
    }
}
