package org.config;

import org.config.factory.*;
import org.config.primitive.ConfigBoolean;
import org.config.primitive.ConfigInteger;
import org.config.primitive.ConfigString;
import org.config.primitive.ConfigStrlist;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class NodeManager {

    private static Map<String, INodeFactory> nodeMap = new HashMap<>();

    public static void init(){
        nodeMap.put("PTH", PathNodeFactory.getInstance());
        nodeMap.put("EQ", EqualsNodeFactory.getInstance());
        nodeMap.put("AND", ANDNodeFactory.getInstance());
        nodeMap.put("OR", ORNodeFactory.getInstance());
        nodeMap.put("NOT", NOTNodeFactory.getInstance());
        nodeMap.put("BOOL", new INodeFactory() {
            @Override
            public Node create(Object json) {
                return createConfigBoolean(json);
            }
        });
        nodeMap.put("INT", new INodeFactory() {
            @Override
            public Node create(Object json) {
                return createConfigInteger(json);
            }
        });
        nodeMap.put("STR", new INodeFactory() {
            @Override
            public Node create(Object json) {
                return createConfigString(json);
            }
        });
        nodeMap.put("STRLIST", new INodeFactory() {
            @Override
            public Node create(Object json) {
                return createStrlist(json);
            }
        });
    }

    public static Node createNode(JSONObject json) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        Set<String> keySet = json.keySet();
        String key = json.keys().next();
        System.out.println(key);
        Node root = null;
        Object child = json.get(key);
        System.out.println("    child: "+child);

        if(!nodeMap.containsKey(key)){
            System.out.println("cannot identify key: "+ key);
            throw new IllegalAccessException();
        }
        root = nodeMap.get(key).create(child);

        if(root==null)
            System.out.println("Error in Creating Node");
        return root;
    }

    // using raw type Operation here
//    public static Operation addArg(Operation node, JSONArray jsonArray) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
//        for(int i=0;i<jsonArray.length();++i){
//            node.put(createNode(jsonArray.getJSONObject(i)));
//        }
//        return node;
//    }


   /*
     detecting type of value in NodeManager itself
     instead of having a ConfigBoolean(Object) constructor
     so as to detect the casting error in NodeManager itself
    */
    private static ConfigBoolean createConfigBoolean(Object value){
        return new ConfigBoolean((Boolean)value);
    }

    private static ConfigInteger createConfigInteger(Object value){
        return new ConfigInteger((Integer)value);
    }

    private static ConfigString createConfigString(Object value){
        return new ConfigString(String.valueOf(value));
    }

    public static ConfigStrlist createStrlist(Object list){
        return new ConfigStrlist((JSONArray)list);
    }
}
