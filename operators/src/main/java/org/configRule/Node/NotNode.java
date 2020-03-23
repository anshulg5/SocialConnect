package org.configRule.Node;

import org.example.Node;
import org.example.NodeFactory;

import java.util.Iterator;
import java.util.Map;

public class NotNode implements Node<Boolean> {

    Node<Boolean> node;

    public NotNode(Map<NodeFactory, Object> map, Map<String,Object> symbolTable) {
        if(map.size()==1) {
            Iterator<NodeFactory> iterator = map.keySet().iterator();
            while (iterator.hasNext()) {
                NodeFactory key = iterator.next();
                node = key.getInstance(map.get(key),symbolTable);
            }
        }
    }

    @Override
    public Boolean apply(Map<String, ?> input) {
        return !node.apply(input);
    }
}
