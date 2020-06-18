package com.flock.frule.configRule.nodes;

import com.flock.frule.NodeManager;
import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonArray;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InvalidObjectException;

public class LessThanNode implements Node<Boolean> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String TYPE = "LESS";
    private Node<Integer> left,right;

    public LessThanNode(JsonObject json) throws InvalidObjectException, IllegalAccessException {
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
            throw new IllegalArgumentException("Expected JsonArray");
        }
    }

    @Override
    public Boolean apply(JsonType input) {
        try{
            return left.apply(input) < right.apply(input);
        } catch(Exception e){
            log.info(e.toString());
            return false;
        }
    }
}
