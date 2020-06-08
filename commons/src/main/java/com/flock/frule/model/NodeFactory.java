package com.flock.frule.model;

import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;
import java.util.Map;

public interface NodeFactory {
    Node getInstance(Object value, Map<String,Object> symbolTable) throws IllegalAccessException;
    Node getInstance(Object value) throws IllegalAccessException;
    Node getInstance(JsonType json) throws InvalidObjectException, IllegalAccessException;
}
