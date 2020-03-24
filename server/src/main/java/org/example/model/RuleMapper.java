package org.example.model;


import org.example.model.Rule;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RuleMapper implements RowMapper<Rule> {

    @Override
    public Rule mapRow(ResultSet resultSet, int i) throws SQLException {
        Rule rule = new Rule();
        rule.setID(resultSet.getString("ID"));
        rule.setID(resultSet.getString("JSONString"));
        return rule;
    }
}
