package com.flock.frule.configRule.nodes;

import com.flock.frule.NodeManager;
import com.flock.frule.model.Node;
import com.flock.frule.model.jsondata.JsonArray;
import com.flock.frule.model.jsondata.JsonObject;
import com.flock.frule.model.jsondata.JsonType;

import java.io.InvalidObjectException;
import java.util.*;

public class AndNode implements Node<Boolean> {
    public static final String TYPE = "AND";
    private Collection< Node<Boolean>> nodeCollection;

    public AndNode(JsonObject json) throws InvalidObjectException, IllegalAccessException {
        JsonType arg = json.get(TYPE);
        nodeCollection = new ArrayList<>();
        if(arg.isArray()){
            JsonArray jsonArray = arg.asArray();
            int size = jsonArray.size();
            for(int i=0;i<size;++i){
                nodeCollection.add(NodeManager.create(jsonArray.get(i)));
            }
        } else {
            throw new InvalidObjectException("Expected JsonArray");
        }

    }

    public AndNode(List<Map<String,Object>> ruleMap, Map<String,Object> symbolTable) throws IllegalAccessException {
        nodeCollection = new ArrayList<>();
        Iterator<Map<String, Object>> iterator = ruleMap.iterator();
        while(iterator.hasNext()){
            Map<String, Object> map = iterator.next();
            if(map.size() == 1) {
                String key = map.keySet().iterator().next();
                Node<Boolean> node = NodeManager.create(key,map.get(key),symbolTable);
                nodeCollection.add(node);
            }
            else {
                System.out.println("Invalid 'AND' format");
            }
        }
    }

    public AndNode(List<Map<String,Object>> ruleMap) throws IllegalAccessException {
        nodeCollection = new ArrayList<>();
        Iterator<Map<String, Object>> iterator = ruleMap.iterator();
        while(iterator.hasNext()){
            Map<String, Object> map = iterator.next();
            if(map.size() == 1) {
                Node<Boolean> node = NodeManager.create(map);
                nodeCollection.add(node);
            }
            else {
                System.out.println("Invalid 'AND' format");
            }
        }
    }

    @Override
    public Boolean apply(JsonType input){
        Boolean result = true;
        for (Node<Boolean> booleanNode : nodeCollection) {
            result &= booleanNode.apply(input);
        }
        return result;
    }
}
