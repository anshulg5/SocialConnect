package com.flock.frule.configRule.nodes;

import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonArray;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class CollectionStringNode implements Node<JsonArray> {
    public static final String TYPE = "STRLIST";
    private final JsonArray jsonArray;

    public CollectionStringNode(JsonType json) throws InvalidObjectException {
        if(json.isArray()) {
            this.jsonArray = json.asArray();
        } else if(json.isObject()){
            this.jsonArray = json.asObject().get(TYPE).asArray();
        } else {
            throw new InvalidObjectException("type-mismatch");
        }
    }

    @Override
    public JsonArray apply(JsonType input) {
        return jsonArray;
    }

}
