package com.flock.frule.configRule.nodes.primitivenodes;

import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonPrimitive;
import com.flock.frule.model.jsondata.JsonType;

public class IntegerNode implements Node<JsonPrimitive> {
    private final static String TYPE = "INT";

    private final JsonPrimitive jsonInteger;


    public IntegerNode(Object value) {
        Integer val;
        if(value instanceof String)
            val = Integer.parseInt((String)value);
        else
            val = (Integer)value;
        jsonInteger = new JsonPrimitive(val);
    }

    public IntegerNode(JsonObject json) {
        JsonType val = json.get(TYPE);
        if(val.isPrimitive())
            jsonInteger = val.asPrimitive();
        else{
            String message = String.format("type-mismatch while creating BooleanNode. EXPECTED: %s, PROVIDED: %s", JsonPrimitive.class, val.getClass());
            throw new RuntimeException(message);
        }
    }

    @Override
    public JsonPrimitive apply(JsonType input) {
        return jsonInteger;
    }
}
