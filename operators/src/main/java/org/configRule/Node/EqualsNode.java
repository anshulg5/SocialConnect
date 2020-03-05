package org.configRule.Node;
import org.example.Node;
import org.example.Operator;

import java.util.*;

public class EqualsNode implements Node<Boolean> {
    Node left,right;

    public EqualsNode(List<Map<Operator, Object>> ruleMap, Map<String, Object> symbolTable) {
        if(ruleMap.size() == 2) {
            Iterator<Map<Operator, Object>> iterator = ruleMap.iterator();
            Map<Operator, Object> map = iterator.next();
            if (map.size() == 1) {
                Map.Entry<Operator, Object> entry = map.entrySet().iterator().next();
                left = entry.getKey().getInstance(entry.getValue(),symbolTable);
            } else {
                System.out.println("Invalid 'eq' format");
            }
            map = iterator.next();
            if (map.size() == 1) {
                Map.Entry<Operator, Object> entry = map.entrySet().iterator().next();
                right = entry.getKey().getInstance(entry.getValue(),symbolTable);
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