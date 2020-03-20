package org.node.primitiveNode;

import org.json.JSONArray;
import org.json.JSONObject;
import org.node.Node;

import java.util.ArrayList;
import java.util.List;

public class ConfigStrlist implements Node<List<String>> {
    List<String> arg;

    public ConfigStrlist(JSONArray jsonArray){
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<jsonArray.length();++i) {
            list.add(jsonArray.getString(i));
        }
        arg = list;
    }

    public ConfigStrlist(List<String> listString){
        arg = listString;
    }

   /*
   public void createFromList(List<String> listString){
        arg = listString;
    }
    public void createFromJSONArray(JSONArray jsonArray){
        ArrayList<String> list = new ArrayList<>();
        for(int i=0;i<jsonArray.length();++i) {
            list.add(jsonArray.getString(i));
        }
        arg = list;
    }

    public ConfigStrlist(Object obj){
        if(obj instanceof JSONArray)
            createFromJSONArray((JSONArray)obj);
        else
            createFromList((List<String>) obj);
    }
    */

    @Override
    public List<String> apply(JSONObject msg) {
        return arg;
    }
}
