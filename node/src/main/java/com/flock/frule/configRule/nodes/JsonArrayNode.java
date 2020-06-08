package com.flock.frule.configRule.nodes;

import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonArray;
import com.flock.frule.model.jsondata.JsonType;

public class JsonArrayNode implements Node<JsonArray> {
    public static final String TYPE = "JSONArr";
    private final JsonArray jsonArray;

    public JsonArrayNode(JsonArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    @Override
    public JsonArray apply(JsonType input) {
        return jsonArray;
    }
}
