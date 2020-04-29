package org.example.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Node;
import org.example.NodeManager;
import org.example.dao.RuleDao;
import org.example.model.Rule;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class RuleApp {
    private RuleDao ruleDao;
    List<Node> sampleRuleList = new ArrayList<>();
    Map<String, Rule> rulesMap;

    @Inject
    RuleApp(RuleDao ruleDao){
        // load ruleList from db
        this.ruleDao = ruleDao;
        rulesMap = new HashMap<>();
    }

    @Inject
    public void init(){
        rulesMap = ruleDao.getRules();
    }

    public Boolean addRule(String ruleID, Map<String, Object> ruleMap) throws IllegalAccessException, InstantiationException, ClassNotFoundException, JsonProcessingException {
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

    public void deleteAllRules(){
        rulesMap.clear();
        ruleDao.deleteAllRules();
    }

    public Boolean updateRule(String ruleID, Map<String, Object> ruleMap) throws IllegalAccessException, InstantiationException, ClassNotFoundException, JsonProcessingException {
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
            map.put(rule.getID(),rule.getRuleString());
        }
        return map;
    }

    public Map<String, Object> fetchRuleMapById(String id){
        if(!rulesMap.containsKey(id))
            return null;
        return rulesMap.get(id).getRuleMap();
    }

    public Boolean validateByID(String ruleID, Map<String,?> msg){
        return rulesMap.get(ruleID).validate(msg);
    }

    public void loadSampleRules(){
        System.out.println("\nLoading Sample Rule configurations");
        
        try {

            ObjectMapper mapper = new ObjectMapper();

            String rule = "{\"EQ\":[{\"PTH\":{\"STRLIST\":[\"from\",\"firstName\"]}},{\"STR\":\"Anshu\"}]}";
            Map<String, Object> ruleMap = (Map<String, Object>) mapper.readValue(rule,new TypeReference<Map<String,Object>>(){});
            System.out.println(rule);

            String rule2 = "{\"AND\":[{\"EQ\":[{\"PTH\":{\"STRLIST\":[\"from\",\"firstName\"]}},{\"STR\":\"Anshul\"}]},{\"NOT\":[{\"EQ\":[{\"PTH\":{\"STRLIST\":[\"text\"]}},{\"STR\":\"Hi\"}]}]}]}";
            Map<String, Object> ruleMap2 = (Map<String, Object>) mapper.readValue(rule2,new TypeReference<Map<String,Object>>(){});
            System.out.println(rule2);

            String rule3 = "{\"AND\":[{\"EQ\":[{\"PTH\":{\"STRLIST\":[\"from\",\"firstName\"]}},{\"STR\":\"Anshu\"}]},{\"BOOL\":true}]}";
            Map<String, Object> ruleMap3 = (Map<String, Object>) mapper.readValue(rule3,new TypeReference<Map<String,Object>>(){});
            System.out.println(rule3);
            
            
            sampleRuleList.add(NodeManager.create(ruleMap));
            sampleRuleList.add(NodeManager.create(ruleMap2));
            sampleRuleList.add(NodeManager.create(ruleMap3));
        } catch (IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
    }

    public void demo(){
        ObjectMapper mapper = new ObjectMapper();
        String msg = "{\"name\":54,\"arr\":[\"random_str\",[\"path_to\"]],\"path_to\":[\"name\"]}";
        System.out.println(msg);
        Map<String, Object> msgMap = null;

        try {
            msgMap = (Map<String, Object>) mapper.readValue(msg,new TypeReference<Map<String,Object>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }

        String rule = "{\"EQ\":[{\"PTH\":{\"PTH\":{\"PTH\":{\"STRLIST\":[\"arr\",\"1\"]}}}},{\"INT\":54}]}";
        System.out.println(rule);
        Node config = null;
        try {
            Map<String, Object> ruleMap = (Map<String, Object>) mapper.readValue(rule,new TypeReference<Map<String,Object>>(){});
            config = NodeManager.create(ruleMap);
        } catch (IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
        System.out.println(config.apply(msgMap));

        String rule2 = "{\"EQ\":[{\"PTH\":{\"PTH\":{\"STRLIST\":[\"path_to\"]}}},{\"STR\":\"Anshul\"}]}";
        System.out.println(rule2);
        Node config2 = null;
        try {
            Map<String, Object> ruleMap2 = (Map<String, Object>) mapper.readValue(rule2,new TypeReference<Map<String,Object>>(){});
            config2 = NodeManager.create(ruleMap2);
        } catch (IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
        System.out.println(config2.apply(msgMap));
    }

    public Boolean validateSampleRules(int index, Map<String,?> inp){
        System.out.println("Validating the message on Config "+index);
        return (Boolean) sampleRuleList.get(index).apply(inp);
    }
}
