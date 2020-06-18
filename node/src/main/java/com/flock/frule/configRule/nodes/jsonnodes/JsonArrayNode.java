package com.flock.frule.configRule.nodes.jsonnodes;

import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonArray;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class JsonArrayNode implements Node<JsonArray> {

    public static final String TYPE = "JSONArr";
    private final JsonArray jsonArray;

    public JsonArrayNode(JsonType json) throws InvalidObjectException {
        if(json.isArray()) {
            this.jsonArray = json.asArray();
        } else if(json.isObject()) {
            try{
                this.jsonArray = json.asObject().get(TYPE).asArray();
            } catch(Exception e){
                throw new InvalidObjectException(e.toString());
            }
        } else {
            throw new InvalidObjectException("type-mismatch");
        }
    }

    @Override
    public JsonArray apply(JsonType input) {
        return jsonArray;
    }
}
