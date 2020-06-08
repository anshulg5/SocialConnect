package com.flock.frule.configRule.nodes;

import com.flock.frule.NodeManager;
import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonType;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class PathNode implements Node<JsonType> {

    /*
        {
            "PTH" : {
                "STRLIST" : ["", "", ""]
         
        }


            "PTH" : ["", "", ""]
        }
     */
    private static final String TYPE = "PTH";
    Node<Collection<String>> collectionNode;

//    PathNode(JsonObject json) {
//        JsonType arg = json.get(TYPE);
//        if (arg.isObject()) {
//            collectionNode = NodeManager.create(arg.asObject(), );
//        } else if (arg.isArray(TYPE)) {
//            collectionNode = NodeManager.create(CollectionStringNode.TYPE, arg.asArray(), null);
//        }
//    }

    public PathNode(Map<String, Object> map, Map<String, Object> symbolTable) throws IllegalAccessException {
        if (map.size() == 1) {
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                collectionNode = NodeManager.create(key, map.get(key), symbolTable);
                ;
            }
        }
    }

    public PathNode(Map<String, Object> map) throws IllegalAccessException {
        if (map.size() == 1) {
            Iterator<String> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                collectionNode = NodeManager.create(map);
                ;
            }
        }
    }


    //TODO: Object type of key might be changed to String type
    // and add null check
    @Override
    public JsonType apply(JsonType input) {
        Collection<String> collection = collectionNode.apply(input);

        JsonType current = input;
        for (Object key : collection) {
            if (current.isArray()){
                int index = (key instanceof String?Integer.parseInt((String)key):(Integer)key);
                current = current.asArray().get(index);
            }else if (current.isObject()){
                current = current.asObject().get((String)key);
            }else {
                throw new RuntimeException();
            }
        }
        return current;
    }

}
