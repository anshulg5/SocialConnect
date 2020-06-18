package com.flock.frule.configRule.nodes.primitivenodes;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.jsonnodes.JsonPrimitiveNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonPrimitive;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class StringNode implements Node<String> {
    public static final String TYPE = "STR";
    private final Node<JsonPrimitive> arg;

    public StringNode(JsonObject json) throws InvalidObjectException, IllegalAccessException {
        JsonType arg = json.get(TYPE);
        if(arg.isObject())
            this.arg = NodeManager.create(arg);
        else if(arg.isPrimitive())
            this.arg = new JsonPrimitiveNode(arg.asPrimitive());
        else
            throw new InvalidObjectException("type-mismatch");
    }


    @Override
    public String apply(JsonType input) {
        return arg.apply(input).getAsString();
    }
}
