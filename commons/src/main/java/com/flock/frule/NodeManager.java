package com.flock.frule;

import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InvalidObjectException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class NodeManager {
    private static Logger log = LoggerFactory.getLogger(NodeManager.class);

    private static HashMap<String, NodeFactory> hashMap = new HashMap();

    public static void registerNodeFactory(String nodeType, NodeFactory nodeFactory){
        hashMap.put(nodeType, nodeFactory);
    }

    public static NodeFactory getNodeFactory(String nodeType){
        return hashMap.get(nodeType);
    }

    public static Boolean containsNode(String nodeType) {
        return hashMap.containsKey(nodeType);
    }

    public static Node create(String nodeType, Object nodeValue, Map<String,Object> symbolTable) throws IllegalAccessException {
        if(!hashMap.containsKey(nodeType)){
            System.out.println("cannot identify key: "+ nodeType);
            throw new IllegalAccessException();
        }
        NodeFactory nodeFactory = getNodeFactory(nodeType);
        return nodeFactory.getInstance(nodeValue,symbolTable);
    }

    public static Node create(Map<String, Object> ruleMap, Map<String,Object> symbolTable) throws IllegalAccessException {
        String nodeType = ruleMap.keySet().iterator().next();
        Object nodeValue = ruleMap.get(nodeType);
        if(!hashMap.containsKey(nodeType)){
            System.out.println("cannot identify key: "+ nodeType);
            throw new IllegalAccessException();
        }
        NodeFactory nodeFactory = getNodeFactory(nodeType);
        return nodeFactory.getInstance(nodeValue,symbolTable);
    }

    public static Node create(Map<String, Object> ruleMap) throws IllegalAccessException {
        String nodeType = ruleMap.keySet().iterator().next();
        Object nodeValue = ruleMap.get(nodeType);
        System.out.println(nodeType);
        System.out.println("    child: "+nodeValue);
        if(!hashMap.containsKey(nodeType)){
            System.out.println("cannot identify key: "+ nodeType);
            throw new IllegalAccessException();
        }
        NodeFactory nodeFactory = getNodeFactory(nodeType);
        return nodeFactory.getInstance(nodeValue);
    }

    public static Node create(JsonType json) throws IllegalAccessException, InvalidObjectException {
        if(json.isObject()){
            JsonObject jsonObject = json.asObject();
            Set<String> keySet = jsonObject.getKeys();
            if(keySet.size()!=1){
                throw new InvalidObjectException("Only one key expected in the JsonObject");
            }
            String nodeType = keySet.iterator().next();
            log.debug(nodeType);
            log.debug("     child:" + jsonObject.get(nodeType));
            if(!hashMap.containsKey(nodeType))
                throw new IllegalAccessException("cannot identify key: "+ nodeType);
            return getNodeFactory(nodeType).getInstance(jsonObject);
        }
        else
            throw new InvalidObjectException("Expected JsonObject");
    }
    
    public static Node create(String nodeType, JsonType json) throws IllegalAccessException, InvalidObjectException {
        log.debug(nodeType);
        log.debug("     child:" + json);
        if(!hashMap.containsKey(nodeType))
            throw new IllegalAccessException("cannot identify key: "+ nodeType);
        return getNodeFactory(nodeType).getInstance(json);
    }
}
