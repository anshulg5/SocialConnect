package com.flock.frule.configRule.nodes;

import com.flock.frule.model.JsonData;
import com.flock.frule.model.Node;

import java.util.Map;

public class VarNode<T> implements Node<T> {
    private String name;
    Map<String,Object> symbolTable;

    public VarNode(String name, Map<String,Object> symbolTable) {
        this.name = name;
        this.symbolTable = symbolTable;
    }

    @Override
    public T apply(JsonData input) {
        return (T) symbolTable.get(name);
    }
}
