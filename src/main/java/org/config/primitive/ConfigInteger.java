package org.config.primitive;

import org.config.Node;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

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
