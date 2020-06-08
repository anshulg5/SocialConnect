package com.flock.frule.configRule.nodes;


import com.flock.frule.NodeManager;
import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonArray;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;
import java.util.ArrayList;
import java.util.Collection;

public class OrNode implements Node<Boolean> {
    public static final String TYPE = "OR";
    private Collection< Node<Boolean>> nodeCollection;

    public OrNode(JsonObject json) throws InvalidObjectException, IllegalAccessException {
        JsonType arg = json.get(TYPE);
        nodeCollection = new ArrayList<>();
        if(arg.isArray()){
            JsonArray jsonArray = arg.asArray();
            int size = jsonArray.size();
            for(int i=0;i<size;++i){
                nodeCollection.add(NodeManager.create(jsonArray.get(i)));
            }
        } else {
            throw new InvalidObjectException("Expected JsonArray");
        }

    }

    @Override
    public Boolean apply(JsonType bindings) {
        Boolean result = false;
        for (Node<Boolean> booleanNode : nodeCollection) result |= booleanNode.apply(bindings);
        return result;
    }

}
