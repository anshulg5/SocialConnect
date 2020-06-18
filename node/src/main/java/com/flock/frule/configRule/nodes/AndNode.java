package com.flock.frule.configRule.nodes;

import com.flock.frule.NodeManager;
import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonArray;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Collection;

public class AndNode implements Node<Boolean> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    public static final String TYPE = "AND";
    private Collection< Node<Boolean>> nodeCollection;

    public AndNode(JsonObject json) throws InvalidObjectException, IllegalAccessException {
        JsonType arg = json.get(TYPE);
        nodeCollection = new ArrayList<>();
        if(arg.isArray()){
            JsonArray jsonArray = arg.asArray();
            for(JsonType elem: jsonArray)
                nodeCollection.add(NodeManager.create(elem));
        } else {
            throw new IllegalArgumentException("Expected JsonArray");
        }

    }

    @Override
    public Boolean apply(JsonType input){
        boolean andResult = true;
        for (Node<Boolean> booleanNode : nodeCollection) {
            try{
                andResult = andResult && booleanNode.apply(input);
            }catch(Exception e){
                log.info(e.toString());
                return false;
            }
        }
        return andResult;
    }
}
