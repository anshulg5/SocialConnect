package com.flock.frule.configRule.nodes;

import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonType;

public class IdentityNode implements Node<JsonType> {
    @Override
    public JsonType apply(JsonType input) {
        return input;
    }
}
