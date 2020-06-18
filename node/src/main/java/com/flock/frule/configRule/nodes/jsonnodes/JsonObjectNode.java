package com.flock.frule.configRule.nodes.jsonnodes;

import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonType;

public class JsonObjectNode implements Node<JsonObject> {
    public static final String TYPE = "JSONObj";
    private final JsonObject jsonObject;

    public JsonObjectNode(JsonType json) {
        if(json.isObject()) {
            JsonObject temp;
            try{
                temp = json.asObject().get("TYPE").asObject();
            } catch(Exception e){
                temp = json.asObject();
            }
            this.jsonObject = temp;
        } else {
            throw new IllegalArgumentException("type-mismatch");
        }
    }

    @Override
    public JsonObject apply(JsonType input) {
        return jsonObject;
    }
}
