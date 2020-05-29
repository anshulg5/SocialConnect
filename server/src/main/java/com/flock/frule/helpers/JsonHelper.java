package com.flock.frule.helpers;

import com.flock.frule.NodeManager;
import com.flock.frule.model.JsonData;
import com.flock.frule.model.Node;

import java.util.*;

public class JsonHelper {

    public static <T> T createCopy(T jsonDataElement, JsonData input) throws IllegalAccessException {
        if(jsonDataElement instanceof Map){
            Map<String, Object> map = (Map) jsonDataElement;
            Map<String, Object> copy = new HashMap<>();
            for(Map.Entry<String, Object> entry: map.entrySet()) {
                String k = entry.getKey();
                Object v = entry.getValue();
                if(isNodeSpec(v))
                    copy.put(k,NodeManager.create((Map) v));
                else if(v instanceof Node)
                    copy.put(k,((Node)v).apply(input));
                else
                    copy.put(k,createCopy(v,input));
            }
            return (T)copy;
        }
        if(jsonDataElement instanceof List){
            List list = (List) jsonDataElement;
            List<Object> copy = new ArrayList<>();
            for(Object elem: list){
                if(isNodeSpec(elem))
                    copy.add(NodeManager.create((Map) elem));
                else if(elem instanceof Node)
                    copy.add(((Node)elem).apply(input));
                else
                    copy.add(createCopy(elem,input));
            }
            return (T)copy;
        }
        return (T)jsonDataElement;
    }

    private static Boolean isNodeSpec(Object obj){
        if(obj instanceof Map){
            Map<String, Object> map = (Map) obj;
            Set<String> keySet = map.keySet();
            if (keySet.size() == 1 && NodeManager.containsNode(keySet.iterator().next()))
                return true;
        }
        return false;
    }
}
