package org.node;

import org.json.JSONArray;
import org.json.JSONObject;
import org.node.primitiveNode.ConfigStrlist;

import java.util.Collection;

public class Eval {

    private Eval(){}

    static <T> T evalPath(JSONObject msg, Collection<String> path){
        Object obj = msg;

        for(String s: path){
            if(obj instanceof JSONObject){
                obj = ((JSONObject) obj).get(s);
            }
            else if(obj instanceof JSONArray){
                if(isInteger(s)){
                    obj = ((JSONArray) obj).get(Integer.valueOf(s));
                }
                else
                    System.out.println("Error in the path: Invalid index type for JSONArray");
            }
            else
                System.out.println("Error in the path: reached the end before");
        }

        if(obj instanceof JSONArray)
            obj = new ConfigStrlist((JSONArray)obj).apply(msg);

        return (T)obj;
    }

    static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException | NullPointerException e) {
            return false;
        }
        return true;
    }

}
