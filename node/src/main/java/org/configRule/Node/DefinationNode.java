package org.configRule.Node;

import org.example.Node;
import org.example.NodeFactory;

import java.util.*;

public class DefinationNode implements Node<List<String>> {

    Map<String,Node> list;
    Map<String,Object> symbolTable;

    public DefinationNode(List< Map<String,Object>> ruleMap, Map<String, Object> symbolTable) {
//        list = new HashMap<>();
//        this.symbolTable = symbolTable;
//        Iterator<Map<Operator, Object>> iterator =  ruleMap.iterator();
//        while(iterator.hasNext()){
//            Map<Operator,Object> map = iterator.next();
//            if(map.size()==2){
//                String name = (String) map.get(Operator);
//                Map<Operator,Object> map1 = (Map<Operator, Object>) map.get(Operator.VALUE);
//                Node node = null;
//                if(map1.size() == 1) {
//                    Operator key = map1.keySet().iterator().next();
//                    node = RuleApp.createNode(key,map1.get(key),symbolTable);
//                }
//                list.put(name,node);
//            }
//            else {
//                System.out.println("Invalid");
//            }
//        }
    }

    @Override
    public List<String> apply(Map<String, ?> input) {
        Iterator<String> iterator = list.keySet().iterator();
        List<String> ans = new ArrayList<>();
        while(iterator.hasNext()){
            String key = iterator.next();
            ans.add(key);
            Object value = list.get(key).apply(input);
            symbolTable.put(key,value);
        }
        return ans;
    }
}
