package org.example.rule;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Node;
import org.example.NodeManager;


import java.io.IOException;
import java.util.Map;

public class Rule {
    String ID;
    Map<String, Object> ruleMap;
    String ruleString;
    Node rootNode;
    static ObjectMapper mapper = new ObjectMapper();

    public Rule(String ID, Map<String, Object> ruleMap) throws IllegalAccessException, InstantiationException, ClassNotFoundException, JsonProcessingException {
        this.ID = ID;
        this.ruleMap = ruleMap;
        rootNode = NodeManager.create(ruleMap);
        this.ruleString = mapper.writeValueAsString(ruleMap);
        System.out.println(ruleString);
    }

    public Rule(String ID, String ruleString) throws IllegalAccessException, InstantiationException, ClassNotFoundException, IOException {
        this.ID =ID;
        this.ruleMap = (Map<String, Object>) mapper.readValue(ruleString,new TypeReference<Map<String,Object>>(){});
        this.ruleString = ruleString;
        rootNode = NodeManager.create(ruleMap);
    }

    public Boolean validate(Map<String, ?> input){
        return (Boolean) rootNode.apply(input);
    }

    public String getID(){
        return ID;
    }

    Map<String, Object> getRuleMap(){
        return ruleMap;
    }

    public String getString(){
        return ruleString;
    }

    void setID(String newID){
        ID = newID;
    }

    void setRuleMap(Map<String, Object> ruleMap) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.ruleMap = ruleMap;
        rootNode = NodeManager.create(ruleMap);
    }
}
