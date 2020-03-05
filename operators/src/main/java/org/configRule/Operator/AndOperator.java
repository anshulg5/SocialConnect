package org.configRule.Operator;

import org.configRule.Node.AndNode;
import org.example.Node;
import org.example.Operator;

import java.util.List;
import java.util.Map;

public class AndOperator implements Operator {
    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) {
        return new AndNode((List<Map<Operator, Object>>) value,symbolTable);
    }
}
