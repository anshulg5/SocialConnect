package org.configRule.Operator;

import org.configRule.Node.CollectionStringNode;
import org.example.Node;
import org.example.Operator;
import org.example.OperatorManager;

import java.util.Collection;
import java.util.Map;

public class CollectionStringOperator implements Operator {
    static {
        CollectionStringOperator collectionStringOperator = new CollectionStringOperator();
        OperatorManager.registerOperator("SLIST",collectionStringOperator);
    }
    @Override
    public Node getInstance(Object value, Map<String, Object> symbolTable) {
        return new CollectionStringNode((Collection<String>) value);
    }
}
