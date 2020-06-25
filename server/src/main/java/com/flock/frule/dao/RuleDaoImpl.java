package com.flock.frule.dao;


import com.flock.frule.model.Rule;
import com.google.inject.Inject;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Singleton;
import java.util.Map;

@Singleton
public class RuleDaoImpl implements RuleDao {
    private final JdbcTemplate db;

    @Inject
    RuleDaoImpl(JdbcTemplate db) {
        this.db = db;
    }

    public Map<String, Rule> getRules(){
        String queryString = "SELECT rule.id, rule.json, target.target_id, target.target_json" +
                " FROM rule LEFT JOIN target" +
                " ON rule.id = target.rule_id";
        return db.query(queryString, new RuleMapExtractor());
    }

    @Override
    public void addRule(Rule rule){
        String insertQuery = "INSERT INTO rule (id,json) VALUES (?,?)";
        db.update(insertQuery,rule.getID(),rule.getRuleString());
    }

    @Override
    public void updateRule(String ruleId, Rule rule) {
        String updateQuery = "UPDATE rule SET json = ? WHERE id = ?";
        db.update(updateQuery,rule.getRuleString(),ruleId);
    }

    @Override
    public void deleteRule(String ruleId){
        String removeString = "DELETE FROM rule WHERE id = ?";
        db.update(removeString,ruleId);
    }
}
