package org.example.configRule;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class GreaterThanNode implements Node<Boolean> {
    Node left,right;

    public GreaterThanNode(List<Map<Operator, Object>> ruleMap, Map<String, Node> symbolTable) {
        if(ruleMap.size() == 2) {
            Iterator<Map<Operator, Object>> iterator = ruleMap.iterator();
            Map<Operator, Object> map = iterator.next();
            if (map.size() == 1) {
                Map.Entry<Operator, Object> entry = map.entrySet().iterator().next();
                left = RuleApp.createNode(entry.getKey(), entry.getValue(),symbolTable);
            } else {
                System.out.println("Invalid 'GT' format");
            }
            map = iterator.next();
            if (map.size() == 1) {
                Map.Entry<Operator, Object> entry = map.entrySet().iterator().next();
                right = RuleApp.createNode(entry.getKey(), entry.getValue(),symbolTable);
            } else {
                System.out.println("Invalid 'GT' format");
            }

        }
        else {
            System.out.println("Invalid 'GT' format");
        }
    }


    @Override
    public Boolean apply(Map<String, ?> input) {
        return null;
    }
}
