package com.flock.frule.configRule.nodes.primitivenodes;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.jsonnodes.JsonPrimitiveNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonPrimitive;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class IntegerNode implements Node<Integer> {
    public static final String TYPE = "INT";
    private final Node<JsonPrimitive> arg;

    public IntegerNode(JsonObject json) throws InvalidObjectException, IllegalAccessException {
        JsonType arg = json.get(TYPE);
        if(arg.isObject())
            this.arg = NodeManager.create(arg);
        else if(arg.isPrimitive())
            this.arg = new JsonPrimitiveNode(arg.asPrimitive());
        else
            throw new IllegalArgumentException("type-mismatch");
    }


    @Override
    public Integer apply(JsonType input) {
        return arg.apply(input).getAsInteger();
    }
}
