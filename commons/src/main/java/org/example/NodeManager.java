package org.example;

import java.util.*;

public class NodeManager {

    private static HashMap<String, NodeFactory> hashMap = new HashMap();

    public static void registerNodeFactory(String name, NodeFactory nodeFactory){
        hashMap.put(name, nodeFactory);
    }

    public static NodeFactory getNodeFactory(String name){
        return hashMap.get(name);
    }

    public static Node parse(String nodeName, Object nodeValue, Map<String,Object> symbolTable) {
        if(!hashMap.containsKey(nodeName)){
            System.out.println("cannot identify key: "+ nodeName);
            // Throw exceptions
        }
        NodeFactory nodeFactory = getNodeFactory(nodeName);
        Node node = nodeFactory.getInstance(nodeValue,symbolTable);
        return node;
    }
}
