package com.flock.frule.app;

import com.flock.frule.model.Response;
import com.flock.frule.model.Rule;
import com.flock.frule.model.Target;
import com.flock.frule.model.jsondata.JsonType;

import java.util.Map;

public interface RuleService {

    Response addRule(String id, Rule rule);
    Response updateRule(String id, Rule rule);
    Response deleteRule(String id);
    Map<String, Rule> fetchRules();
    Response addTarget(String ruleId, Target target);
    Response removeTarget(String ruleId, String targetId);
    Response validateByID(String id, JsonType input);
    void applyInput(JsonType input);
}
