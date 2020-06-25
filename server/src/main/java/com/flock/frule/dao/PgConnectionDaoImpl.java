package com.flock.frule.dao;

import com.flock.frule.model.ConnectionDetail;
import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Singleton
public class PgConnectionDaoImpl implements ConnectionDetailDao {

    private final JdbcTemplate db;

    @Inject
    public PgConnectionDaoImpl(JdbcTemplate db) {
        this.db = db;
    }

    @Override
    public List<ConnectionDetail> getAllConnections() {
        return db.query("SELECT * from connection", new ConnectionMapper());
    }

    @Override
    public boolean saveDetails(ConnectionDetail detail) {
        return db.update("INSERT into connection(source_id,target_id) values(?,?)",detail.getSourceID(),detail.getTargetID()) > 0;
    }



}
