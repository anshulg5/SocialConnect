package org.config;

import org.config.primitive.ConfigBoolean;
import org.config.primitive.ConfigInteger;
import org.config.primitive.ConfigString;
import org.config.primitive.ConfigStrlist;
import org.json.JSONArray;
import org.json.JSONObject;

public class NodeFactory {
    public static Node createNode(JSONObject json) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
//        Set<String> keySet = json.keySet();
        String key = json.keys().next();
        System.out.println(key);
        Node root = null;
        Object child = json.get(key);
        System.out.println("    child: "+child);
        switch (key){
            case "PTH":
                root = createPathNode(child);
                break;
            case "EQ":
                root = createEqualsNode(child);
                break;
            case "AND":
                root = createAndNode(child);
                break;
            case "OR":
                root = createOrNode(child);
                break;
            case "NOT":
                root = createNotNode(child);
                break;
            case "BOOL":
                root = createConfigBoolean(child);
                break;
            case "INT":
                root = createConfigInteger(child);
                break;
            case "STR":
                root = createConfigString(child);
                break;
            case "STRLIST":
                root = createStrlist(child);
        }
        if(root==null)
            System.out.println("Error in Creating Node");
        return root;
    }

    private static PathNode createPathNode(Object json) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        PathNode pathNode;
        if(json instanceof JSONObject){
            pathNode = new PathNode(createNode((JSONObject)json));
        }
        else {
            pathNode = new PathNode(createStrlist(json));
        }
        return pathNode;
    }

    private static EqualsNode createEqualsNode(Object json) throws IllegalAccessException, ClassNotFoundException, InstantiationException {
        return (EqualsNode)addArg(new EqualsNode(), (JSONArray)json);
    }

    private static ANDNode createAndNode(Object json) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return (ANDNode)addArg(new ANDNode(), (JSONArray)json);
    }

    private static ORNode createOrNode(Object json) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return (ORNode)addArg(new ORNode(), (JSONArray)json);
    }

    private static NOTNode createNotNode(Object json) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return (NOTNode)addArg(new NOTNode(), (JSONArray)json);
    }

    // using raw type Operation here
    private static Operation addArg(Operation node, JSONArray jsonArray) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        for(int i=0;i<jsonArray.length();++i){
            node.put(createNode(jsonArray.getJSONObject(i)));
        }
        return node;
    }


   /*
     detecting type of value in NodeFactory itself
     instead of having a ConfigBoolean(Object) constructor
     so as to detect the casting error in NodeFactory itself
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

    private static ConfigStrlist createStrlist(Object list){
        return new ConfigStrlist((JSONArray)list);
    }
}
