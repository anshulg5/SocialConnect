package com.flock.frule.configRule.nodes.primitivenodes;

import com.flock.frule.model.JsonData;
import com.flock.frule.model.Node;

public class BooleanNode implements Node<Boolean> {
    private Boolean value;

    public BooleanNode(Boolean value) {
        this.value = value;
    }
    public BooleanNode(String value) {
        this.value = Boolean.valueOf(value);
    }
    public BooleanNode(Object value) {
        if(value instanceof String)
            this.value = Boolean.valueOf((String) value);
        else
            this.value = (Boolean) value;

    }

    public void setValue(String value) {
        this.value = Boolean.valueOf(value);
    }

    @Override
    public Boolean apply(JsonData bindings) {
        return this.value;
    }
}
