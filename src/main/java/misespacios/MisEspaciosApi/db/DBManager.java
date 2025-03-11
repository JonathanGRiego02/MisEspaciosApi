package misespacios.MisEspaciosApi.db;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;

public class DBManager {

    private Utils utils = new Utils();
    private final String url;
    private final String user;
    private final String password;

    private HikariDataSource dataSource;


    public DBManager() {
        url = utils.getProperty("url");
        user = utils.getProperty("user");
        password = utils.getProperty("password");

        HikariConfig config = loadConfig();
        dataSource = new HikariDataSource(config);
    }

    private HikariConfig loadConfig() {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(url);
        config.setUsername(user);
        config.setPassword(password);
        config.setMaximumPoolSize(10);
        config.setMinimumIdle(2);
        config.setIdleTimeout(30000);
        config.setMaxLifetime(600000);
        config.setConnectionTimeout(30000);
        return config;
    }

    // Get a connection from the pool
    public Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Close de pool of connections
    public void closeConnection() {
        dataSource.close();
    }

    public static void main(String[] args) {
        DBManager dbManager = new DBManager();
        Connection connection = dbManager.getConnection();
        dbManager.closeConnection();
    }



}
