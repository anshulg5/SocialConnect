package com.flock.frule.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.flock.frule.dao.RuleDao;
import com.flock.frule.model.Rule;
import com.flock.frule.model.jsondata.JsonType;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class RuleApp {
    private RuleDao ruleDao;
    Map<String, Rule> rulesMap;

    @Inject
    RuleApp(RuleDao ruleDao){
        this.ruleDao = ruleDao;
        rulesMap = new HashMap<>();
    }

    @Inject
    public void init(){
        rulesMap = ruleDao.getRules();
    }

    public Boolean addRule(String ruleID, Map<String, Object> ruleMap) throws IllegalAccessException, JsonProcessingException {
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

    public Boolean updateRule(String ruleID, Map<String, Object> ruleMap) throws IllegalAccessException, JsonProcessingException {
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

    public Boolean validateByID(String ruleID, JsonType input){
        Boolean match;
        try{
            match =  rulesMap.get(ruleID).validate(input);
        } catch (Exception e) {
            match = false;
        }
        return match;
    }

}
