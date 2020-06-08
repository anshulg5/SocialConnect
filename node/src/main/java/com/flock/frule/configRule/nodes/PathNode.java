package com.flock.frule.configRule.nodes;

import com.flock.frule.NodeManager;
import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonArray;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonPrimitive;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;
import java.util.Iterator;
import java.util.Map;

public class PathNode implements Node<JsonType> {

    /*
        {
            "PTH" : {
                "PTH" : ["", "", ""]
            }
        }

        {
            "PTH" : ["", "", ""]
        }
     */
    public static final String TYPE = "PTH";
    private Node<JsonArray> collectionNode;

    public PathNode(JsonObject json) throws InvalidObjectException, IllegalAccessException {
        JsonType arg = json.get(TYPE);
        if (arg.isObject()) {
            collectionNode = NodeManager.create(arg.asObject());
        } else if (arg.isArray()) {
            collectionNode = NodeManager.create(JsonArrayNode.TYPE, arg.asArray());
        }
    }

    public PathNode(Map<String, Object> map, Map<String, Object> symbolTable) throws IllegalAccessException {
        if (map.size() == 1) {
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                collectionNode = NodeManager.create(key, map.get(key), symbolTable);
            }
        }
    }

    public PathNode(Map<String, Object> map) throws IllegalAccessException {
        if (map.size() == 1) {
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                collectionNode = NodeManager.create(map);
            }
        }
    }


    //TODO: add null check
    @Override
    public JsonType apply(JsonType input) {
        JsonType returnedValue = collectionNode.apply(input);
        if(returnedValue.isNull())
            return returnedValue;

        JsonArray collection = returnedValue.asArray();
        int size = collection.size();
        JsonType current = input;

        for (int i=0;i<size;++i) {
            JsonPrimitive collectionElem = collection.get(i).asPrimitive();
            if (current.isArray()){
                int index = collectionElem.getAsInteger();
                current = current.asArray().get(index);
            }else if (current.isObject()){
                String key = collectionElem.getAsString();
                current = current.asObject().get(key);
            }else if(current.isNull()) {
                return current;
            }else {
                throw new RuntimeException();
            }
        }
        return current;
    }

}
