package com.flock.frule.configRule.nodes.jsonnodes;

import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonPrimitive;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class JsonPrimitiveNode implements Node<JsonPrimitive> {
    public static final String TYPE = "JSONPrimitive";
    private final JsonPrimitive jsonPrimitive;

    public JsonPrimitiveNode(JsonType json) throws InvalidObjectException {
        if(json.isPrimitive()) {
            this.jsonPrimitive = json.asPrimitive();
        } else if(json.isObject()) {
            try {
                this.jsonPrimitive = json.asObject().get(TYPE).asPrimitive();
            } catch (Exception e){
                throw new InvalidObjectException(e.toString());
            }
        } else {
            throw new InvalidObjectException("type-mismatch");
        }
    }

    @Override
    public JsonPrimitive apply(JsonType input) {
        return jsonPrimitive;
    }
}
