package org.example.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Node;
import org.example.NodeManager;


import java.io.IOException;
import java.util.Map;

public class Rule {
    private String ID;
    private Map<String, Object> ruleMap;
    private String ruleString;
    private Node rootNode;
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

    public Rule() {

    }

    public Boolean validate(Map<String, ?> input){
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

    void setRuleMap(Map<String, Object> ruleMap) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        this.ruleMap = ruleMap;
        rootNode = NodeManager.create(ruleMap);
    }
}
