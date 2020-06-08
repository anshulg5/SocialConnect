package com.flock.frule.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flock.frule.NodeManager;
import com.flock.frule.model.jsondata.JsonType;

import java.io.IOException;
import java.util.Map;

public class Rule {
    private String ID;
    private Map<String, Object> ruleMap;
    private String ruleString;
    private Node rootNode;
    static ObjectMapper mapper = new ObjectMapper();

    public Rule(String ID, Map<String, Object> ruleMap) throws IllegalAccessException, JsonProcessingException {
        this.ID = ID;
        this.ruleMap = ruleMap;
        rootNode = NodeManager.create(ruleMap);
        this.ruleString = mapper.writeValueAsString(ruleMap);
        System.out.println(ruleString);
    }

    public Rule(String ID, String ruleString) throws IllegalAccessException, IOException {
        this.ID =ID;
        this.ruleMap = mapper.readValue(ruleString,new TypeReference<Map<String,Object>>(){});
        this.ruleString = ruleString;
        rootNode = NodeManager.create(ruleMap);
    }

    public Boolean validate(JsonType input){
        return (Boolean) rootNode.apply(input);
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public Map<String, Object> getRuleMap() {
        return ruleMap;
    }

    public String getRuleString() {
        return ruleString;
    }

    public void setRuleString(String ruleString) {
        this.ruleString = ruleString;
    }

    public Node getRootNode() {
        return rootNode;
    }

    public void setRootNode(Node rootNode) {
        this.rootNode = rootNode;
    }

    void setRuleMap(Map<String, Object> ruleMap) throws IllegalAccessException {
        this.ruleMap = ruleMap;
        rootNode = NodeManager.create(ruleMap);
    }
}
