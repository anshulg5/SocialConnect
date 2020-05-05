package org.example.dao;

import org.example.model.ConnectionDetail;

import java.util.List;


public interface ConnectionDetailDao {
    List<ConnectionDetail> getAllConnections();
    boolean saveDetails(ConnectionDetail detail);
}