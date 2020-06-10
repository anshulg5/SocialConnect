package com.flock.frule.model;

import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public interface NodeFactory {
    Node getInstance(JsonType json) throws InvalidObjectException, IllegalAccessException;
}
