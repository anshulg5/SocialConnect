package org.example.configRule;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DefinationNode implements Node<List<String>>{

    List<String>list;

    public DefinationNode(List< Map<Operator,Object>> ruleMap, Map<String, Node> symbolTable) {
        list = new ArrayList<>();
        Iterator<Map<Operator, Object>> iterator =  ruleMap.iterator();
        while(iterator.hasNext()){
            Map<Operator,Object> map = iterator.next();
            if(map.size()==2 && map.containsKey(Operator.NAME) && map.containsKey(Operator.VALUE)){
                String name = (String) map.get(Operator.NAME);
                list.add(name);
                Map<Operator,Object> map1 = (Map<Operator, Object>) map.get(Operator.VALUE);
                Node node = null;
                if(map1.size() == 1) {
                    Operator key = map1.keySet().iterator().next();
                    node = RuleApp.createNode(key,map1.get(key),symbolTable);
                }
                symbolTable.put(name,node);
            }
            else {
                System.out.println("Invalid");
            }
        }
    }

    @Override
    public List<String> apply(Map<String, ?> input) {
        return list;
    }
}
