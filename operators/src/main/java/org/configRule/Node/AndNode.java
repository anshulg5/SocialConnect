package org.configRule.Node;

import org.example.Node;
import org.example.Operator;
import java.util.*;

public class AndNode implements Node<Boolean> {

    Collection< Node<Boolean> > nodeCollection;

    public AndNode(List<Map<Operator,Object>> ruleMap, Map<String,Object> symbolTable) {
        nodeCollection = new ArrayList<>();
        Iterator<Map<Operator, Object>> iterator = ruleMap.iterator();
        while(iterator.hasNext()){
            Map<Operator,Object> map = iterator.next();
            if(map.size() == 1) {
                Operator key = map.keySet().iterator().next();
                Node<Boolean> node = key.getInstance(map.get(key),symbolTable);
                nodeCollection.add(node);
            }
            else {
                System.out.println("Invalid 'AND' format");
            }
        }
    }

    @Override
    public Boolean apply(Map<String, ?> input){
        Boolean result = true;
        Iterator<Node<Boolean>> iterator = nodeCollection.iterator();
        while(iterator.hasNext()) {
            result &= iterator.next().apply(input);
        }
        return result;
    }
}
