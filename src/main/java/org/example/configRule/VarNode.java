package org.example.configRule;

import java.util.Map;

public class VarNode<T> implements Node<T> {
    private String name;
    private Node node;

    public VarNode(String name, Map<String,Node> symbolTable) {
        this.name = name;
        node = symbolTable.get(name);
    }

    @Override
    public T apply(Map<String, ?> input) {
        return (T) node.apply(input);
    }
}
