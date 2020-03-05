package org.configRule.Node;

import org.example.Node;
import org.example.Operator;

import java.util.Iterator;
import java.util.Map;

public class NotNode implements Node<Boolean> {

    Node<Boolean> node;

    public NotNode(Map<Operator, Object> map, Map<String,Object> symbolTable) {
        if(map.size()==1) {
            Iterator<Operator> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                Operator key = iterator.next();
                node = key.getInstance(map.get(key),symbolTable);
            }
        }
    }

    @Override
    public Boolean apply(Map<String, ?> input) {
        return !node.apply(input);
    }
}
