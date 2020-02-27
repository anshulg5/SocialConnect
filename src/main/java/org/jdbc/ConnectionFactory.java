package org.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    String driverClassName = "org.postgresql.Driver";
    String connectionUrl = "jdbc:postgresql://localhost:5432/SocialConnect";
    String dbUser = "root";
    String dbPwd = "root";

    private static ConnectionFactory connectionFactory = null;

    private ConnectionFactory() {
        try {
            Class.forName(driverClassName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection() throws SQLException {
        Connection conn = null;
        conn = DriverManager.getConnection(connectionUrl);
        return conn;
    }

    public static Connection getConnection() throws SQLException {
        if (connectionFactory == null) {
            connectionFactory = new ConnectionFactory();
        }
        return connectionFactory.createConnection();
    }
}
