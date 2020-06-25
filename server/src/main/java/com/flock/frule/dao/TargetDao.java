package com.flock.frule.dao;

import com.flock.frule.model.Target;

public interface TargetDao {
    void addTarget(String ruleId, Target target);
    void removeTarget(String ruleId, String targetId);
}
