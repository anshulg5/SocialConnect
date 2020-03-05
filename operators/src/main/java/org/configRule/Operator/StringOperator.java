package org.configRule.Operator;

import org.configRule.Node.primitiveNode.StringNode;
import org.example.Node;
import org.example.Operator;
import org.example.OperatorManager;

import java.util.Map;

public class StringOperator implements Operator {
    static {
        StringOperator stringOperator = new StringOperator();
        OperatorManager.registerOperator("STR",stringOperator);
    }
    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) {
        return new StringNode((String) value);
    }
}
