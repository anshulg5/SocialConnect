package org.example.rule;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;

import org.node.Node;
import org.node.NodeManager;

import java.io.IOException;
import java.util.Map;

public class Rule {
    String ID;
    Map<String, Object> ruleMap;
    Node rootNode;

    public Rule(String ID, Map<String, Object> ruleMap) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.ID = ID;
        this.ruleMap = ruleMap;
        rootNode = NodeManager.createNode(ruleMap);
    }

    public Rule(String ID, String jsonString) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException {
        this.ID =ID;
        ObjectMapper mapper = new ObjectMapper();
        this.ruleMap = (Map<String, Object>) mapper.readValue(jsonString,new TypeReference<Map<String,Object>>(){});
        rootNode = NodeManager.createNode(ruleMap);
    }

    public Boolean validate(JSONObject msg){
        return (Boolean) rootNode.apply(msg);
    }

    public String getID(){
        return ID;
    }

    Map<String, Object> getRuleMap(){
        return ruleMap;
    }

    public String getJsonString(){
        return ruleMap.toString();
    }

    void setID(String newID){
        ID = newID;
    }

    void setRuleMap(Map<String, Object> ruleMap) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.ruleMap = ruleMap;
        rootNode = NodeManager.createNode(ruleMap);
    }
}
