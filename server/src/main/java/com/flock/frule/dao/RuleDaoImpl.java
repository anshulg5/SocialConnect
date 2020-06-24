package com.flock.frule.dao;


import com.flock.frule.model.Rule;
import com.flock.frule.model.RuleMapper;
import com.google.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class RuleDaoImpl implements RuleDao {
    private final JdbcTemplate db;

    @Inject
    RuleDaoImpl(JdbcTemplate db) {
        this.db = db;
    }

    public Map<String, Rule> getRules(){
        Map<String, Rule> map = new HashMap<>();
        List<Rule> list = db.query("SELECT * from RULE", new RuleMapper());
        for (Rule rule : list) {
            map.put(rule.getID(), rule);
        }
        return map;
    }

    @Override
    public void addRule(Rule rule){
        String insertString = "INSERT INTO RULE (ID,JSONString) VALUES (?,?)";
        db.update(insertString,rule.getID(),rule.getRuleString());
    }

    @Override
    public void updateRule(String ruleId, Rule rule) {
        String updateString = "UPDATE RULE SET JSONString = ? WHERE ID = ?";
        db.update(updateString,rule.getRuleString(),ruleId);
    }

    @Override
    public void deleteRule(String ruleId){
        String removeString = "DELETE FROM RULE WHERE ID = ?";
        db.update(removeString,ruleId);
    }
}
