package org.example.db.dao;
import org.example.model.Rule;
import java.util.Map;

public interface RuleDao {
    public Map<String, Rule> getRules();
    public void addRule(Rule rule);
    public void deleteRule(String ruleID);
}
