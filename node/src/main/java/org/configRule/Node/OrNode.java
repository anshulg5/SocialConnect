package org.configRule.Node;


import com.flock.frule.model.JsonData;
import org.example.Node;
import org.example.NodeFactory;

import java.util.*;

public class OrNode implements Node<Boolean> {

    Collection< Node<Boolean> > nodeCollection;

    public OrNode(List<Map<NodeFactory,Object>> ruleMap, Map<String,Object> symbolTable) throws IllegalAccessException {
        nodeCollection = new ArrayList<>();
        Iterator<Map<NodeFactory, Object>> iterator = ruleMap.iterator();
        while(iterator.hasNext()){
            Map<NodeFactory,Object> map = iterator.next();
            if(map.size() == 1) {
                NodeFactory key = map.keySet().iterator().next();
                Node<Boolean> node = key.getInstance(map.get(key),symbolTable);
                nodeCollection.add(node);
            }
            else {
                System.out.println("Invalid 'OR' format");
            }
        }
    }

    @Override
    public Boolean apply(JsonData bindings) {
        Boolean result = false;
        Iterator< Node<Boolean> > iterator = nodeCollection.iterator();
        while(iterator.hasNext()) result |= iterator.next().apply(bindings);
        return result;
    }

}
