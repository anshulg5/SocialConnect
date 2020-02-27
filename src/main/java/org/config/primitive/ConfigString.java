package org.config.primitive;

import org.config.Node;
import org.json.JSONObject;

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
