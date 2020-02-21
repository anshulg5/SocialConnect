package org.example.configRule;

import java.util.Map;

public class VarNode<T> implements Node<T> {
    private String name;
    Map<String,Object> symbolTable;

    public VarNode(String name, Map<String,Object> symbolTable) {
        this.name = name;
        this.symbolTable = symbolTable;
    }

    @Override
    public T apply(Map<String, ?> input) {
        return (T) symbolTable.get(name);
    }
}
