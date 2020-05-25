package org.configRule.Node.primitiveNode;

import com.flock.frule.model.JsonData;
import org.example.Node;

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
