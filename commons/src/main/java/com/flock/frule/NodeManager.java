package com.flock.frule;

import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InvalidObjectException;
import java.util.HashMap;
import java.util.Set;

public class NodeManager {
    private static Logger log = LoggerFactory.getLogger(NodeManager.class);

    private static HashMap<String, NodeFactory> hashMap = new HashMap<>();

    public static void registerNodeFactory(String nodeType, NodeFactory nodeFactory){
        hashMap.put(nodeType, nodeFactory);
    }

    public static NodeFactory getNodeFactory(String nodeType){
        return hashMap.get(nodeType);
    }

    public static Boolean containsNode(String nodeType) {
        return hashMap.containsKey(nodeType);
    }

    public static <T> Node<T> create(JsonType json) throws IllegalAccessException, InvalidObjectException {
        if(json.isObject()){
            JsonObject jsonObject = json.asObject();
            Set<String> keySet = jsonObject.getKeys();
            if(keySet.size()!=1){
                throw new InvalidObjectException("Only one key expected in the JsonObject");
            }
            String nodeType = keySet.iterator().next();
            log.debug(nodeType + " : " + jsonObject.get(nodeType));
            if(!hashMap.containsKey(nodeType))
                throw new IllegalAccessException("cannot identify key: "+ nodeType);
            return (Node<T>) getNodeFactory(nodeType).getInstance(jsonObject);
        } else if(json.isPrimitive()){
            return (Node<T>) getNodeFactory("JSONPrimitive").getInstance(json);
        } else if(json.isArray()){
            return (Node<T>) getNodeFactory("JSONArr").getInstance(json);
        } else{ // json is of type JsonNull
            return (Node<T>) getNodeFactory("JsonNull").getInstance(json);
        }

    }
    
    public static <T> Node<T> create(String nodeType, JsonType json) throws IllegalAccessException, InvalidObjectException {
        log.debug(nodeType + " : " + json);
        if(!hashMap.containsKey(nodeType))
            throw new IllegalAccessException("cannot identify key: "+ nodeType);
        return (Node<T>) getNodeFactory(nodeType).getInstance(json);
    }
}
