package com.flock.frule.dao;

import com.flock.frule.model.Rule;
import com.flock.frule.model.Target;
import com.flock.frule.util.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.io.InvalidObjectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class RuleMapExtractor implements ResultSetExtractor<Map<String, Rule>> {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public Map<String, Rule> extractData(ResultSet rs) throws SQLException, DataAccessException {
        Map<String,Rule> rulesMap = new HashMap<>();
        while(rs.next()){
            String ruleId = rs.getString("id");
            Target target = null;
            String targetJsonString = rs.getString("target_json");
            if(null != targetJsonString) {
                try {
                    target = Target.Builder.fromJson(
                            Serializer.fromJson(targetJsonString)
                                    .asObject()
                    );
                } catch (InvalidObjectException | IllegalAccessException e) {
                    log.error(e.toString());
                }
            }
            Rule rule = rulesMap.get(ruleId);
            if(null == rule){
                try {
                    rule = new Rule(ruleId, Serializer.fromJson(rs.getString("json")));
                    if(null!=target) rule.addTarget(target);
                    rulesMap.put(ruleId,rule);
                } catch (InvalidObjectException | IllegalAccessException e) {
                    log.error(e.toString());
                }
            } else if(null!=target){
                rule.addTarget(target);
            }
        }
        return rulesMap;
    }
}
