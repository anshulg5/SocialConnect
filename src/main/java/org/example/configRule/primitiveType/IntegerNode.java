package org.example.configRule.primitiveType;

import org.example.configRule.Node;
import org.json.JSONObject;

import java.util.Map;

public class IntegerNode implements Node<Integer> {

    private Integer value;

    public IntegerNode(String value) {
        this.value = Integer.parseInt(value);
    }

    public IntegerNode(Integer value) {
        this.value =value;
    }

    public void setValue(String value) {
        this.value = Integer.parseInt(value);
    }

    @Override
    public Integer apply(Map<String, ?> bindings) {
        return this.value;
    }
}
