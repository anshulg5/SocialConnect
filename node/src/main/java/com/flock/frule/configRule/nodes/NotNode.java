package com.flock.frule.configRule.nodes;

import com.flock.frule.NodeManager;
import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonArray;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InvalidObjectException;

public class NotNode implements Node<Boolean> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    public static final String TYPE = "NOT";
    private Node<Boolean> arg;

    public NotNode(JsonObject json) throws InvalidObjectException, IllegalAccessException {
        JsonType arg = json.get(TYPE);
        if(arg.isArray()){
            JsonArray jsonArray = arg.asArray();
            int size = jsonArray.size();
            if(size!=1) {
                throw new InvalidObjectException("Exactly one element expected in the JsonArray");
            }
            this.arg = NodeManager.create(jsonArray.get(0));
        } else {
            throw new InvalidObjectException("Expected JsonArray");
        }

    }

    @Override
    public Boolean apply(JsonType input) {
        try {
            return !arg.apply(input);
        } catch(Exception e){
            log.info(e.toString());
            return false;
        }
    }
}
