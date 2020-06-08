package com.flock.frule.model;

import com.flock.frule.model.jsondata.JsonType;

public interface Node<T>{
    T apply(JsonType input);
}
