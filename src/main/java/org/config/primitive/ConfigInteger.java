package org.config.primitive;

import org.config.Node;
import org.json.JSONObject;

public class ConfigInteger implements Node<Integer> {
    Integer arg;

    public ConfigInteger(Integer value){
        arg = value;
    }

    ConfigInteger(String value){
        arg = Integer.valueOf(value);
    }

    @Override
    public Integer apply(JSONObject msg) {
        return arg;
    }
}
