package com.flock.frule.configRule.nodes;

import com.flock.frule.NodeManager;
import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonArray;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class GreaterThanNode implements Node<Boolean> {
    private static final String TYPE = "GTR";
    private Node left,right;

    public GreaterThanNode(JsonObject json) throws InvalidObjectException, IllegalAccessException {
        JsonType arg = json.get(TYPE);
        if(arg.isArray()){
            JsonArray jsonArray = arg.asArray();
            int size = jsonArray.size();
            if(size!=2) {
                throw new InvalidObjectException("Exactly two elements expected in the JsonArray");
            }
            left = NodeManager.create(jsonArray.get(0));
            right = NodeManager.create(jsonArray.get(1));
        } else {
            throw new InvalidObjectException("Expected JsonArray");
        }
    }

    @Override
    public Boolean apply(JsonType input) {
        return null;
    }
}
