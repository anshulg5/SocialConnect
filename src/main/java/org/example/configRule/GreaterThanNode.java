package org.example.configRule;

import java.util.Iterator;
import java.util.Map;

public class GreaterThanNode implements Node<Boolean> {
    Node left,right;

    public GreaterThanNode(Map<Operator, Object> ruleMap) {
        if(ruleMap.size() == 2) {
            Iterator<Operator> iterator = ruleMap.keySet().iterator();
            Operator key = iterator.next();
            left = RuleApp.createNode(key,ruleMap.get(key));
            key = iterator.next();
            right = RuleApp.createNode(key,ruleMap.get(key));
        }
        else {
            System.out.println("Invalid 'EQ' format");
        }
    }

    @Override
    public Boolean apply(Map<String, ?> input) {
        return null;
    }
}
