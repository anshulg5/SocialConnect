package com.flock.frule.configRule.nodes;

import com.flock.frule.NodeManager;
import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonType;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GreaterThanNode implements Node<Boolean> {

    private Node left,right;

    public GreaterThanNode(List<Map<String, Object>> ruleMap, Map<String, Object> symbolTable) throws IllegalAccessException {
        if(ruleMap.size() == 2) {
            Iterator<Map<String, Object>> iterator = ruleMap.iterator();
            Map<String, Object> map = iterator.next();
            if (map.size() == 1) {
                Map.Entry<String, Object> entry = map.entrySet().iterator().next();
                left = NodeManager.create(entry.getKey(),entry.getValue(),symbolTable);
            } else {
                System.out.println("Invalid 'gt' format");
            }
            map = iterator.next();
            if (map.size() == 1) {
                Map.Entry<String, Object> entry = map.entrySet().iterator().next();
                right = NodeManager.create(entry.getKey(),entry.getValue(),symbolTable);
            } else {
                System.out.println("Invalid 'gt' format");
            }
        }
        else {
            System.out.println("Invalid 'gt' format");
        }
    }

    @Override
    public Boolean apply(JsonType input) {
        return null;
    }
}
