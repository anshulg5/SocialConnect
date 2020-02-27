package org.config;

import org.config.primitive.ConfigStrlist;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.inject.Singleton;
import java.util.Collection;

@Singleton
public class Eval {

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
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }

}
