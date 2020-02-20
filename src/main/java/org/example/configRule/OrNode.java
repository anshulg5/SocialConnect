package org.example.configRule;

import com.google.inject.internal.cglib.core.$MethodInfoTransformer;
import org.json.JSONObject;

import java.util.*;

public class OrNode implements Node<Boolean>{

    Collection< Node<Boolean> > nodeCollection;

    public OrNode(List<Map<Operator,Object>> ruleMap,Map<String,Node> symbolTable) {
        nodeCollection = new ArrayList<>();
        Iterator<Map<Operator, Object>> iterator = ruleMap.iterator();
        while(iterator.hasNext()){
            Map<Operator,Object> map = iterator.next();
            if(map.size() == 1) {
                Map.Entry<Operator,Object> entry = map.entrySet().iterator().next();
                Node<Boolean> node = RuleApp.createNode(entry.getKey(),entry.getValue(),symbolTable);
                nodeCollection.add(node);
            }
            else {
                System.out.println("Invalid 'OR' format");
            }
        }
    }

    @Override
    public Boolean apply(Map<String, ?> bindings) {
        Boolean result = false;
        Iterator< Node<Boolean> > iterator = nodeCollection.iterator();
        while(iterator.hasNext()) result |= iterator.next().apply(bindings);
        return result;
    }

}
