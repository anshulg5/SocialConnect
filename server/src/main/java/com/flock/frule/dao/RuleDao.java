package com.flock.frule.dao;

import com.flock.frule.model.Rule;

import java.util.Map;

public interface RuleDao {
    Map<String, Rule> getRules();
    void addRule(Rule rule);
    void updateRule(String ruleId, Rule rule);
    void deleteRule(String ruleId);
}
