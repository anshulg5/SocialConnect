package org.configRule.Node.primitiveNode;

import org.example.Node;

import java.util.Map;

public class IntegerNode implements Node<Integer> {

    private Integer value;

    public IntegerNode(String value) {
        this.value = Integer.parseInt(value);
    }

    public IntegerNode(Integer value) {
        this.value =value;
    }

    public IntegerNode(Object value) {
        if(value instanceof String)
            this.value = Integer.parseInt((String)value);
        else
            this.value = (Integer)value;
    }

    public void setValue(String value) {
        this.value = Integer.parseInt(value);
    }

    @Override
    public Integer apply(Map<String, ?> bindings) {
        return this.value;
    }
}
