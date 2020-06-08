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

public class EqualsNode implements Node<Boolean> {
    private static final String TYPE = "EQ";
    private Node left,right;

    public EqualsNode(JsonObject json) throws InvalidObjectException, IllegalAccessException {
        JsonType arg = json.get(TYPE);
        if(arg.isArray()){
            JsonArray jsonArray = arg.asArray();
            int size = jsonArray.size();
            if(size!=2) {
                throw new InvalidObjectException("Exactly two elements expected in the JsonArray");
            }
            left = NodeManager.create(jsonArray.get(0));
            right = NodeManager.create(jsonArray.get(1));
        } else {
            throw new InvalidObjectException("Expected JsonArray");
        }
    }

    public EqualsNode(List<Map<String, Object>> ruleMap, Map<String, Object> symbolTable) throws IllegalAccessException {
        if(ruleMap.size() == 2) {
            Iterator<Map<String, Object>> iterator = ruleMap.iterator();
            Map<String, Object> map = iterator.next();
            if (map.size() == 1) {
                Map.Entry<String, Object> entry = map.entrySet().iterator().next();
                left = NodeManager.create(entry.getKey(),entry.getValue(),symbolTable);
            } else {
                System.out.println("Invalid 'eq' format");
            }
            map = iterator.next();
            if (map.size() == 1) {
                Map.Entry<String, Object> entry = map.entrySet().iterator().next();
                right = NodeManager.create(entry.getKey(),entry.getValue(),symbolTable);
            } else {
                System.out.println("Invalid 'eq' format");
            }

        }
        else {
            System.out.println("Invalid 'eq' format");
        }
    }

    public EqualsNode(List<Map<String, Object>> ruleMap) throws IllegalAccessException {
        if(ruleMap.size() == 2) {
            Iterator<Map<String, Object>> iterator = ruleMap.iterator();
            Map<String, Object> map = iterator.next();
            if (map.size() == 1) {
                left = NodeManager.create(map);
            } else {
                System.out.println("Invalid 'eq' format");
            }
            map = iterator.next();
            if (map.size() == 1) {
                right = NodeManager.create(map);
            } else {
                System.out.println("Invalid 'eq' format");
            }

        }
        else {
            System.out.println("Invalid 'eq' format");
        }
    }

    @Override
    public Boolean apply(JsonType input) {
        if (left.apply(input).equals(right.apply(input)))
            return true;
        return false;
    }
}