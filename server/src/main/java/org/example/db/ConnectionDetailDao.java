package org.example.db;

import com.google.inject.Singleton;
import org.example.model.ConnectionDetail;

import java.util.List;


public interface ConnectionDetailDao {
    List<ConnectionDetail> getAllConnections();
    boolean saveDetails(ConnectionDetail detail);
}
