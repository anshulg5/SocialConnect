package com.flock.frule.configRule.nodes;

import com.flock.frule.NodeManager;
import com.flock.frule.configRule.nodes.jsonnodes.JsonArrayNode;
import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonArray;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonPrimitive;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;

public class PathNode<T> implements Node<T> {

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
            collectionNode = new JsonArrayNode(arg.asArray());
        } else {
            throw new InvalidObjectException("type-mismatch");
        }
    }

    @Override
    public T apply(JsonType input) {
        JsonArray path = collectionNode.apply(input);

        JsonType current = input;
        for (JsonType p: path) {
            JsonPrimitive collectionElem = p.asPrimitive();
            if (current.isArray()){
                int index = collectionElem.getAsInteger();
                current = current.asArray().get(index);
            }else if (current.isObject()){
                String key = collectionElem.getAsString();
                current = current.asObject().get(key);
            }else {
                throw new IllegalArgumentException("cannot iterate over path on given input: " + input);
            }
        }
        return (T) current;
    }

}
