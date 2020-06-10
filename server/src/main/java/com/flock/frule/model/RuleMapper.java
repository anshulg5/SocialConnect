package com.flock.frule.model;


import com.flock.frule.util.Serializer;
import org.springframework.jdbc.core.RowMapper;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RuleMapper implements RowMapper<Rule> {

    @Override
    public Rule mapRow(ResultSet resultSet, int i) throws SQLException {
        String ID = resultSet.getString("ID");
        String JSONString = resultSet.getString("JSONString");
        Rule rule = null;
        try {
            rule = new Rule(ID, Serializer.fromJson(JSONString));
        } catch (IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
        return rule;
    }
}
