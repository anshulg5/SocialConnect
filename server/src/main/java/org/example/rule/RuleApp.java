package org.example.rule;

import org.database.dao.RuleDao;
import org.example.Node;
import org.json.JSONObject;

import org.node.NodeManager;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class RuleApp {
    RuleDao ruleDao;
    List<Node> sampleRuleList = new ArrayList<>();
    Map<String, Rule> rulesMap;

    @Inject
    RuleApp(RuleDao ruleDao){
        // load ruleList from db
        this.ruleDao = ruleDao;
        rulesMap = new HashMap<>();
        init();
    }

    private void init(){
        rulesMap = ruleDao.getRules();
    }

    public Boolean addRule(String ruleID, Map<String, Object> ruleMap) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        if(rulesMap.containsKey(ruleID)) {
            System.out.println("rule already there");
            return false;
        }
        Rule rule = new Rule(ruleID,ruleMap);
        ruleDao.addRule(rule);
        rulesMap.put(ruleID,rule);
        return true;
    }

    public Boolean deleteRule(String ruleID){
        if(!rulesMap.containsKey(ruleID)){
            System.out.println("non-existent rule");
            return false;
        }
        rulesMap.remove(ruleID);
        ruleDao.deleteRule(ruleID);
        return true;
    }

    public Boolean updateRule(String ruleID, Map<String, Object> ruleMap) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        if(!rulesMap.containsKey(ruleID)) {
            System.out.println("non-existent rule");
            return false;
        }
        ruleDao.deleteRule(ruleID);
        Rule rule = new Rule(ruleID,ruleMap);
        ruleDao.addRule(rule);
        rulesMap.put(ruleID,rule);
        return true;

    }

//    public Boolean changeID

    public Map<String, String> fetchRules(){
        Map<String, String> map = new HashMap<>();
        for(Rule rule : rulesMap.values()){
            map.put(rule.getID(),rule.getJsonString());
        }
        return map;
    }

    public Boolean validateByID(String ruleID, JSONObject msg){
        return rulesMap.get(ruleID).validate(msg);
    }

    public void loadSampleRules(){
        System.out.println("\nLoading Sample Rule configurations");

        JSONObject json = new JSONObject("{\"EQ\":[{\"PTH\":[\"from\",\"firstName\"]},{\"STR\":\"Anshu\"}]}");
        System.out.println(json);

        JSONObject json2 = new JSONObject("{\"AND\":[{\"EQ\":[{\"PTH\":[\"from\",\"firstName\"]},{\"STR\":\"Anshul\"}]},{\"NOT\":[{\"EQ\":[{\"PTH\":[\"text\"]},{\"STR\":\"Hi\"}]}]}]}");
        System.out.println(json2);

        JSONObject json3 = new JSONObject("{\"AND\":[{\"EQ\":[{\"PTH\":[\"from\",\"firstName\"]},{\"STR\":\"Anshu\"}]},{\"BOOL\":true}]}");
        System.out.println(json3);

        try {
            sampleRuleList.add(NodeManager.createNode(json));
            sampleRuleList.add(NodeManager.createNode(json2));
            sampleRuleList.add(NodeManager.createNode(json3));
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    public void demo(){
        JSONObject jsonMsg2 = new JSONObject("{\"name\":54,\"arr\":[\"random_str\",[\"path_to\"]],\"path_to\":[\"name\"]}");

        JSONObject json4 = new JSONObject("{\"EQ\":[{\"PTH\":{\"PTH\":{\"PTH\":[\"arr\",\"1\"]}}},{\"INT\":54}]}");
        System.out.println(json4);
        Node config4 = null;
        try {
            config4 = NodeManager.createNode(json4);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println(config4.apply(jsonMsg2));

        JSONObject json5 = new JSONObject("{\"EQ\":[{\"PTH\":{\"PTH\":[\"path_to\"]}},{\"STR\":\"Anshul\"}]}");
        System.out.println(json5);
        Node config5 = null;
        try {
            config5 = NodeManager.createNode(json5);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
        System.out.println(config5.apply(jsonMsg2));
    }

    public Boolean validateSampleRules(int index, JSONObject msg){
        System.out.println("Validating the message on Config "+index);
        return (Boolean) sampleRuleList.get(index).apply(msg);
    }
}
