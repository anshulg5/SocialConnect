package org.node.primitiveNode;

import org.json.JSONObject;
import org.node.Node;

public class ConfigString implements Node<String> {
    String arg;

    public ConfigString(Object value){
        arg = String.valueOf(value);
    }

    @Override
    public String apply(JSONObject msg) {
        return arg;
    }
}
