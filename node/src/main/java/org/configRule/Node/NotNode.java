package org.configRule.Node;

import com.flock.frule.model.JsonData;
import org.example.Node;
import org.example.NodeManager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class NotNode implements Node<Boolean> {

    List< Node<Boolean> > nodeCollection;

    public NotNode(List<Map<String,Object>> ruleMap, Map<String,Object> symbolTable) throws IllegalAccessException {
        if(ruleMap.size()==1) {
            nodeCollection = new ArrayList<>();
            Iterator<Map<String, Object>> iterator = ruleMap.iterator();
            while (iterator.hasNext()) {
                Map<String, Object> map = iterator.next();
                if (map.size() == 1) {
                    String key = map.keySet().iterator().next();
                    Node<Boolean> node = NodeManager.create(key, map.get(key), symbolTable);
                    nodeCollection.add(node);
                } else {
                    System.out.println("Invalid 'NOT' format");

                }
            }
        }
        else{
            System.out.println("Invalid 'NOT' format");
        }
    }

    public NotNode(List<Map<String,Object>> ruleMap) throws IllegalAccessException {
        if(ruleMap.size()==1) {
            nodeCollection = new ArrayList<>();
            Iterator<Map<String, Object>> iterator = ruleMap.iterator();
            while (iterator.hasNext()) {
                Map<String, Object> map = iterator.next();
                if (map.size() == 1) {
                    Node<Boolean> node = NodeManager.create(map);
                    nodeCollection.add(node);
                } else {
                    System.out.println("Invalid 'NOT' format");

                }
            }
        }
        else{
            System.out.println("Invalid 'NOT' format");
        }
    }

    @Override
    public Boolean apply(JsonData input) {
        return !nodeCollection.get(0).apply(input);
    }
}
