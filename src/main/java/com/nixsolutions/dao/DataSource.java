package com.nixsolutions.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;

public class DataSource {

    private static BasicDataSource dataSource = new BasicDataSource();

    static {
        Properties props = null;
        try {
            props = new Properties();
            props.load(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("database.properties"));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }

        String driver = (String) props.get("db.driver");
        String url = (String) props.get("db.url");
        String user = (String) props.get("db.user");
        String password = (String) props.get("db.password");
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(user);
        dataSource.setPassword(password);
        dataSource.setMinIdle(5);
        dataSource.setMaxIdle(20);

    }

    protected static Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }

}
