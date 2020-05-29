package com.flock.frule.dao;

import com.flock.frule.model.ConnectionDetail;

import java.util.List;


public interface ConnectionDetailDao {
    List<ConnectionDetail> getAllConnections();
    boolean saveDetails(ConnectionDetail detail);
}
