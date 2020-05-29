package com.flock.frule.configRule.nodes.primitivenodes;

import com.flock.frule.model.JsonData;
import com.flock.frule.model.Node;

public class StringNode implements Node<String> {

    private String value;

    public StringNode(String value) {
        this.value = value;
    }

    @Override
    public String apply(JsonData bindings) {
        return this.value;
    }

}
