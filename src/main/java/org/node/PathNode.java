package org.node;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class PathNode<T> implements Node<T> {
    Node<List<String>> arg;

    public PathNode(Node<List<String>> value){
        this.arg = value;
    }

    @Override
    public T apply(JSONObject msg) {
        Object path = arg.apply(msg);
        if(!(path instanceof List))
            path = convertToList(path);
        return Eval.evalPath(msg, (Collection<String>) path);
    }

    List<String> convertToList(Object obj){
        List<String> list = new LinkedList<>();
        if(obj instanceof JSONArray){
            JSONArray jsonArray = (JSONArray)obj;
            for(int i=0;i<jsonArray.length();++i) {
                list.add(String.valueOf(jsonArray.get(i)));
            }
        }
        else
            list.add(String.valueOf(obj));
        return list;
    }
}
