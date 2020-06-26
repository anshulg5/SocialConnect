package com.flock.frule.configRule.nodes.jsonnodes;

import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonNull;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class JsonNullNode implements Node<JsonNull> {

    public static final String TYPE = "JSONNull";
    private final JsonNull jsonNull;

    public JsonNullNode(JsonType json) throws InvalidObjectException {
        if(json.isNull()) {
            this.jsonNull = json.asNull();
        } else if(json.isObject()) {
            try{
                this.jsonNull = json.asObject().get(TYPE).asNull();
            } catch(Exception e){
                throw new InvalidObjectException(e.toString());
            }
        } else {
            throw new InvalidObjectException("type-mismatch");
        }
    }

    @Override
    public JsonNull apply(JsonType input) {
        return jsonNull;
    }
}