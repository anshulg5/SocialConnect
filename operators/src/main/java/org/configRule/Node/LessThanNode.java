package org.configRule.Node;

import org.example.Node;
import org.example.NodeManager;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LessThanNode implements Node<Boolean> {

    private Node left,right;

    public LessThanNode(List<Map<String, Object>> ruleMap, Map<String, Object> symbolTable) throws IllegalAccessException {
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
    public Boolean apply(Map<String, ?> input) {
        return null;
    }
}
