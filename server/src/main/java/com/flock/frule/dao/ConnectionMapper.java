package com.flock.frule.dao;

import com.flock.frule.model.ConnectionDetail;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionMapper implements RowMapper<ConnectionDetail> {

    public ConnectionDetail mapRow(ResultSet resultSet, int i) throws SQLException {

        ConnectionDetail connectionDetail = new ConnectionDetail();
        connectionDetail.setSourceID(resultSet.getString("source_id"));
        connectionDetail.setTargetID(resultSet.getString("target_id"));
        return connectionDetail;
    }

}
