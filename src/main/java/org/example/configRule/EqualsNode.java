package org.example.configRule;
import org.example.configRule.primitiveType.StringNode;
import org.json.JSONObject;

import javax.xml.stream.events.EndDocument;
import java.nio.file.Path;
import java.util.*;

public class EqualsNode implements Node<Boolean> {
    Node left,right;
    public EqualsNode(){}

    public EqualsNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public EqualsNode(Map<Operator, Object> ruleMap) {
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
        // We can check if object is of same type or not..
        if (left.apply(input).equals(right.apply(input)))
            return true;
        return false;
    }
}