package org.configRule.Node;
import org.example.Node;
import org.example.NodeFactory;
import org.example.NodeManager;

import java.util.*;

public class EqualsNode implements Node<Boolean> {
    private Node left,right;

    public EqualsNode(List<Map<String, Object>> ruleMap, Map<String, Object> symbolTable) {
        if(ruleMap.size() == 2) {
            Iterator<Map<String, Object>> iterator = ruleMap.iterator();
            Map<String, Object> map = iterator.next();
            if (map.size() == 1) {
                Map.Entry<String, Object> entry = map.entrySet().iterator().next();
                left = NodeManager.parse(entry.getKey(),entry.getValue(),symbolTable);
            } else {
                System.out.println("Invalid 'eq' format");
            }
            map = iterator.next();
            if (map.size() == 1) {
                Map.Entry<String, Object> entry = map.entrySet().iterator().next();
                right = NodeManager.parse(entry.getKey(),entry.getValue(),symbolTable);
            } else {
                System.out.println("Invalid 'eq' format");
            }

        }
        else {
            System.out.println("Invalid 'eq' format");
        }
    }

    @Override
    public Boolean apply(Map<String, ?> input) {
        if (left.apply(input).equals(right.apply(input)))
            return true;
        return false;
    }
}