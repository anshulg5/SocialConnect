package com.flock.frule.model;

import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public interface NodeFactory<T> {
    Node<T> getInstance(JsonType json) throws InvalidObjectException, IllegalAccessException;
}
