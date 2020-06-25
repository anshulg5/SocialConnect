package com.flock.frule.dao;

import com.flock.frule.model.Target;
import com.google.inject.Singleton;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.inject.Inject;

@Singleton
public class TargetDaoImpl implements TargetDao{
    private final JdbcTemplate db;

    @Inject
    TargetDaoImpl(JdbcTemplate db) {
        this.db = db;
    }

    @Override
    public void addTarget(String ruleId, Target target) {
        String insertQuery = "INSERT INTO target (target_id, target_json, rule_id) VALUES (?,?,?)";
        db.update(insertQuery,target.getId(),target.getTargetJSonString(),ruleId);
    }

    @Override
    public void removeTarget(String ruleId, String targetId) {
        String removeQuery = "DELETE FROM target WHERE target_id = ? AND rule_id = ?";
        db.update(removeQuery,targetId,ruleId);
    }
}
