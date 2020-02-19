package org.example.configRule;

import java.util.Iterator;
import java.util.Map;

public class NotNode implements Node<Boolean> {

    Node<Boolean> node;

    public NotNode(Map<Operator, Object> map) {
        if(map.size()==1) {
            Iterator<Operator> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                Operator key = iterator.next();
                node = RuleApp.createNode(key, map.get(key));
            }
        }
    }

    @Override
    public Boolean apply(Map<String, ?> input) {
        return !node.apply(input);
    }
}
