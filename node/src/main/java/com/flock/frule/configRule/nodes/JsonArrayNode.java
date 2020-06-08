package com.flock.frule.configRule.nodes;

import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonArray;
import com.flock.frule.model.jsondata.JsonType;

public class JsonArrayNode implements Node<JsonArray> {
    public static final String TYPE = "JSONArr";
    private final JsonArray jsonArray;

    public JsonArrayNode(JsonType json) {
        if(json.isArray()) {
            this.jsonArray = json.asArray();
        } else {
            this.jsonArray = json.asObject().get(TYPE).asArray();
        }
    }

    @Override
    public JsonArray apply(JsonType input) {
        return jsonArray;
    }
}
