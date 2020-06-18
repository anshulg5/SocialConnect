package com.flock.frule.configRule.nodes.jsonnodes;

import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonArray;
import com.flock.frule.model.jsondata.JsonType;

public class JsonArrayNode implements Node<JsonArray> {
    public static final String TYPE = "JSONArr";
    private final JsonArray jsonArray;

    public JsonArrayNode(JsonType json) {
        if(json.isArray()) {
            this.jsonArray = json.asArray();
        } else if(json.isObject()) {
            this.jsonArray = json.asObject().get(TYPE).asArray();
        } else {
            throw new IllegalArgumentException("type-mismatch");
        }
    }

    @Override
    public JsonArray apply(JsonType input) {
        return jsonArray;
    }
}
