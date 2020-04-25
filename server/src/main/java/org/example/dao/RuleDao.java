package org.example.dao;

import org.example.model.Rule;

import java.util.Map;

public interface RuleDao {
    Map<String, Rule> getRules();
    void addRule(Rule rule);
    void deleteRule(String ruleID);
    void deleteAllRules();
}
