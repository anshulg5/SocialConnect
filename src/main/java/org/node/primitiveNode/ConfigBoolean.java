package org.node.primitiveNode;

import org.json.JSONObject;
import org.node.Node;

public class ConfigBoolean implements Node<Boolean> {
    Boolean arg;

    public ConfigBoolean(Boolean value){
        arg = value;
    }

    ConfigBoolean(String value){
        arg = Boolean.valueOf(value);
    }

    @Override
    public Boolean apply(JSONObject msg) {
        return arg;
    }
}
