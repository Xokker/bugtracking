package ru.hse.esadykov;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Ernest Sadykov
 * @since 31.05.2014
 */
public class ConnectionFactory {
    private static BasicDataSource instance;

    public static Connection getConnection() throws SQLException {
        if (instance == null) {
            throw new IllegalStateException("No db connection");
        }

        return instance.getConnection();
    }

    public synchronized static void setUpPool(String host, String port, String user, String password) {
        if (instance != null) {
            return;
        }
        if (host == null || port == null || host.isEmpty() || port.isEmpty()) {
            throw new IllegalArgumentException();
        }

        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        ds.setUsername(user);
        ds.setPassword(password);
        ds.setUrl("jdbc:mysql://" + host + ":" + port + "/bugtracking");

        instance = ds;

        try {
            instance.getConnection();
        } catch (SQLException e) {
            instance = null;
            throw new RuntimeException("instantiation of connection failed");
        }
    }

    public synchronized static void closePool() {
        if (instance == null) {
            return;
        }

        try {
            instance.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
