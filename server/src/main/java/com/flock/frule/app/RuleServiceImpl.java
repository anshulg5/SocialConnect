package com.flock.frule.app;

import com.flock.frule.dao.RuleDao;
import com.flock.frule.model.Response;
import com.flock.frule.model.Rule;
import com.flock.frule.model.Target;
import com.flock.frule.model.jsondata.JsonType;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class RuleServiceImpl implements RuleService {
    private RuleDao ruleDao;
    private final Map<String, Rule> rulesMap;

    @Inject
    RuleServiceImpl(RuleDao ruleDao){
        this.ruleDao = ruleDao;
        rulesMap = new HashMap<>();
    }

    @Inject
    public void init(){
        rulesMap.putAll(ruleDao.getRules());
    }

    @Override
    public Response addRule(String id, Rule rule) {
        Response response = new Response();
        if(rulesMap.containsKey(id)){
            response.setStatus(false);
            response.setMessage("Rule with id: " + id + " already exist");
            return response;
        }
        ruleDao.addRule(rule);
        rulesMap.put(id,rule);
        response.setStatus(true);
        response.setMessage("Rule with id: "+ id + " added");
        return response;
    }

    @Override
    public Response updateRule(String id, Rule rule) {
        Response response = new Response();
        if(!rulesMap.containsKey(id)){
            response.setStatus(false);
            response.setMessage("No rule with id: " + id);
            return response;
        }
        ruleDao.deleteRule(id);
        ruleDao.addRule(rule);
        rulesMap.put(id,rule);
        response.setStatus(true);
        response.setMessage("Rule with id: "+ id + " updated");
        return response;
    }

    @Override
    public Response deleteRule(String id){
        Response response = new Response();
        if(!rulesMap.containsKey(id)){
            response.setStatus(false);
            response.setMessage("No rule with id: " + id);
            return response;
        }
        rulesMap.remove(id);
        ruleDao.deleteRule(id);
        response.setStatus(true);
        response.setMessage("Rule with id: "+ id + " deleted");
        return response;
    }

    @Override
    public Map<String, Rule> fetchRules(){
        return new HashMap<>(rulesMap);
    }

    @Override
    public Response addTarget(String ruleId, Target target) {
        if(!rulesMap.containsKey(ruleId)){
            Response response = new Response();
            response.setStatus(false);
            response.setMessage("No rule with id: " + ruleId);
            return response;
        }
        return rulesMap.get(ruleId).addTarget(target);
    }

    @Override
    public Response removeTarget(String ruleId, String targetId) {
        if(!rulesMap.containsKey(ruleId)){
            Response response = new Response();
            response.setStatus(false);
            response.setMessage("No rule with id: " + ruleId);
            return response;
        }
        return rulesMap.get(ruleId).removeTarget(targetId);
    }

    @Override
    public Response validateByID(String id, JsonType input){
        Response response = new Response();
        if(!rulesMap.containsKey(id)){
            response.setStatus(false);
            response.setMessage("No rule with id: " + id);
            return response;
        }
        return rulesMap.get(id).validate(input);
    }

    @Override
    public void applyInput(JsonType input) {
        rulesMap.forEach((k,v) -> v.apply(input));
    }

}
