package com.flock.frule.configRule.nodes;

import com.flock.frule.NodeManager;
import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonArray;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NotNode implements Node<Boolean> {
    public static final String TYPE = "NOT";
    private Node<Boolean> arg;

    public NotNode(JsonObject json) throws InvalidObjectException, IllegalAccessException {
        JsonType arg = json.get(TYPE);
        if(arg.isArray()){
            JsonArray jsonArray = arg.asArray();
            int size = jsonArray.size();
            if(size!=1) {
                throw new InvalidObjectException("Exactly one element expected in the JsonArray");
            }
            this.arg = NodeManager.create(jsonArray.get(0));
        } else {
            throw new InvalidObjectException("Expected JsonArray");
        }

    }

    public NotNode(List<Map<String,Object>> ruleMap, Map<String,Object> symbolTable) throws IllegalAccessException {
        if(ruleMap.size()==1) {
            Iterator<Map<String, Object>> iterator = ruleMap.iterator();
            while (iterator.hasNext()) {
                Map<String, Object> map = iterator.next();
                if (map.size() == 1) {
                    String key = map.keySet().iterator().next();
                    Node<Boolean> node = NodeManager.create(key, map.get(key), symbolTable);
                    this.arg = node;
                } else {
                    System.out.println("Invalid 'NOT' format");

                }
            }
        }
        else{
            System.out.println("Invalid 'NOT' format");
        }
    }

    public NotNode(List<Map<String,Object>> ruleMap) throws IllegalAccessException {
        if(ruleMap.size()==1) {
            Iterator<Map<String, Object>> iterator = ruleMap.iterator();
            while (iterator.hasNext()) {
                Map<String, Object> map = iterator.next();
                if (map.size() == 1) {
                    Node<Boolean> node = NodeManager.create(map);
                    this.arg = node;
                } else {
                    System.out.println("Invalid 'NOT' format");

                }
            }
        }
        else{
            System.out.println("Invalid 'NOT' format");
        }
    }

    @Override
    public Boolean apply(JsonType input) {
        return !arg.apply(input);
    }
}
