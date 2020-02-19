package org.example.configRule;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class LessThanNode implements Node<Boolean> {

    Node left,right;

    public LessThanNode(List<Map<Operator,Object>> ruleMap){
        if(ruleMap.size() == 2) {
            Iterator<Map<Operator, Object>> iterator = ruleMap.iterator();
            Map<Operator, Object> map = iterator.next();
            if (map.size() == 1) {
                Map.Entry<Operator, Object> entry = map.entrySet().iterator().next();
                left = RuleApp.createNode(entry.getKey(), entry.getValue());
            } else {
                System.out.println("Invalid 'LT' format");
            }
            map = iterator.next();
            if (map.size() == 1) {
                Map.Entry<Operator, Object> entry = map.entrySet().iterator().next();
                right = RuleApp.createNode(entry.getKey(), entry.getValue());
            } else {
                System.out.println("Invalid 'LT' format");
            }

        }
        else {
            System.out.println("Invalid 'LT' format");
        }
    }


    @Override
    public Boolean apply(Map<String, ?> input) {
        return null;
    }
}
