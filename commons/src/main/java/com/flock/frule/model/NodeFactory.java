package com.flock.frule.model;

import java.util.Map;

public interface NodeFactory {
    Node getInstance(Object value, Map<String,Object> symbolTable) throws IllegalAccessException;
    Node getInstance(Object value) throws IllegalAccessException;
}
