package com.flock.frule.configRule.nodes.primitivenodes;

import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonPrimitive;
import com.flock.frule.model.jsondata.JsonType;

public class BooleanNode implements Node<JsonPrimitive> {
    private final static String TYPE = "BOOL";

    private final JsonPrimitive jsonBoolean;

    public BooleanNode(Object value) {
        Boolean val;
        if(value instanceof String)
            val = Boolean.valueOf((String) value);
        else
            val = (Boolean) value;
        jsonBoolean = new JsonPrimitive(val);
    }

    public BooleanNode(JsonObject json) {
        JsonType val = json.get(TYPE);
        if(val.isPrimitive()){
            jsonBoolean = val.asPrimitive();
        }
        else{
            String message = String.format("type-mismatch while creating BooleanNode. EXPECTED: %s, PROVIDED: %s", JsonPrimitive.class, val.getClass());
            throw new RuntimeException(message);
        }
    }

    @Override
    public JsonPrimitive apply(JsonType input) {
        return jsonBoolean;
    }
}
