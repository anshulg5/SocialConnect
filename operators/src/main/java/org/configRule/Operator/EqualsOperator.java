package org.configRule.Operator;

import org.configRule.Node.EqualsNode;
import org.example.Node;
import org.example.Operator;

import java.util.List;
import java.util.Map;

public class EqualsOperator implements Operator {
    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) {
        return new EqualsNode((List<Map<Operator, Object>>) value,symbolTable);
    }
}
