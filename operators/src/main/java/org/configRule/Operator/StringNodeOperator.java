package org.configRule.Operator;

import org.configRule.Node.primitiveNode.StringNode;
import org.example.Node;
import org.example.Operator;
import org.example.OperatorManager;

import java.util.Map;

public class StringNodeOperator implements Operator {
    static {
        StringNodeOperator stringNodeOperator = new StringNodeOperator();
        OperatorManager.registerOperator("STR",stringNodeOperator);
    }
    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) {
        return new StringNode((String) value);
    }
}
