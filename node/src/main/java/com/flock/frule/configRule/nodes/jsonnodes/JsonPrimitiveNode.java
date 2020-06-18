package com.flock.frule.configRule.nodes.jsonnodes;

import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonPrimitive;
import com.flock.frule.model.jsondata.JsonType;

public class JsonPrimitiveNode implements Node<JsonPrimitive> {
    public static final String TYPE = "JSONPrimitive";
    private final JsonPrimitive jsonPrimitive;

    public JsonPrimitiveNode(JsonType json) {
        if(json.isPrimitive()) {
            this.jsonPrimitive = json.asPrimitive();
        } else if(json.isObject()) {
            this.jsonPrimitive = json.asObject().get(TYPE).asPrimitive();
        } else {
            throw new IllegalArgumentException("type-mismatch");
        }
    }

    @Override
    public JsonPrimitive apply(JsonType input) {
        return jsonPrimitive;
    }
}
