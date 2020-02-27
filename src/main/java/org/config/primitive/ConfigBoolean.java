package org.config.primitive;

import org.config.Node;
import org.json.JSONObject;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.List;

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
