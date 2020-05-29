package com.flock.frule;

import com.flock.frule.model.Node;
import com.flock.frule.model.NodeFactory;

import java.util.HashMap;
import java.util.Map;

public class NodeManager {

    private static HashMap<String, NodeFactory> hashMap = new HashMap();

    public static void registerNodeFactory(String name, NodeFactory nodeFactory){
        hashMap.put(name, nodeFactory);
    }

    public static NodeFactory getNodeFactory(String name){
        return hashMap.get(name);
    }

    public static Boolean containsNode(String name) {
        return hashMap.containsKey(name);
    }

    public static Node create(String nodeName, Object nodeValue, Map<String,Object> symbolTable) throws IllegalAccessException {
        if(!hashMap.containsKey(nodeName)){
            System.out.println("cannot identify key: "+ nodeName);
            throw new IllegalAccessException();
        }
        NodeFactory nodeFactory = getNodeFactory(nodeName);
        return nodeFactory.getInstance(nodeValue,symbolTable);
    }

    public static Node create(Map<String, Object> ruleMap, Map<String,Object> symbolTable) throws IllegalAccessException {
        String nodeName = ruleMap.keySet().iterator().next();
        Object nodeValue = ruleMap.get(nodeName);
        if(!hashMap.containsKey(nodeName)){
            System.out.println("cannot identify key: "+ nodeName);
            throw new IllegalAccessException();
        }
        NodeFactory nodeFactory = getNodeFactory(nodeName);
        return nodeFactory.getInstance(nodeValue,symbolTable);
    }

    public static Node create(Map<String, Object> ruleMap) throws IllegalAccessException {
        String nodeName = ruleMap.keySet().iterator().next();
        Object nodeValue = ruleMap.get(nodeName);
        System.out.println(nodeName);
        System.out.println("    child: "+nodeValue);
        if(!hashMap.containsKey(nodeName)){
            System.out.println("cannot identify key: "+ nodeName);
            throw new IllegalAccessException();
        }
        NodeFactory nodeFactory = getNodeFactory(nodeName);
        return nodeFactory.getInstance(nodeValue);
    }
}