package org.configRule.Node.primitiveNode;

import org.example.Node;

import java.util.Map;

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
    public Boolean apply(Map<String, ?> bindings) {
        return this.value;
    }
}
