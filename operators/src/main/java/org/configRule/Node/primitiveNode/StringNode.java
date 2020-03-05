package org.configRule.Node.primitiveNode;

import org.example.Node;

import java.util.Map;

public class StringNode implements Node<String> {

    private String value;

    public StringNode(String value) {
        this.value = value;
    }

    @Override
    public String apply(Map<String, ?> bindings) {
        return this.value;
    }

}
