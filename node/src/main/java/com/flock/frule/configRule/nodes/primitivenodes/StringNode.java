package com.flock.frule.configRule.nodes.primitivenodes;

import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonPrimitive;
import com.flock.frule.model.jsondata.JsonType;

public class StringNode implements Node<JsonPrimitive> {
    private final static String TYPE = "STR";

    private final JsonPrimitive jsonString;


    public StringNode(Object value) {
        jsonString = new JsonPrimitive(String.valueOf(value));
    }

    public StringNode(JsonObject json) {
        JsonType val = json.get(TYPE);
        if(val.isPrimitive())
            jsonString = val.asPrimitive();
        else{
            String message = String.format("type-mismatch while creating BooleanNode. EXPECTED: %s, PROVIDED: %s", JsonPrimitive.class, val.getClass());
            throw new RuntimeException(message);
        }
    }

    @Override
    public JsonPrimitive apply(JsonType input) {
        return jsonString;
    }
}
