package com.nixsolutions.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.commons.dbcp2.BasicDataSource;

public class AbstractJdbcDao {

//    private String url;
//
//    private String dbUser;
//
//    private String password;
//
//    private String driver;
//
//    private BasicDataSource basicDataSource;

//    public AbstractJdbcDao() {
//        setProperties();
//    }


    public Connection createConnection() {
        try {
            return DataSource.getConnection();
        } catch (Exception ex) {
            throw new RuntimeException("Connection is not establish", ex);
        }
    }

//    private Properties getPropertyFile() {
//        Properties props = new Properties();
//
//        try (InputStream inputStream = getClass()
//            .getClassLoader().getResourceAsStream("database.properties")) {
//
//            if (inputStream == null) {
//                throw new IllegalStateException("Couldn't load resource");
//            }
//
//            props.load(inputStream);
//
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//        return props;
//    }
//
//    private void setProperties() {
//        Properties prop = getPropertyFile();
//
//        if (prop == null) {
//            System.out.println("Prop file is not load!");
//            return;
//        }
//
//        url = prop.getProperty("db.url");
//        dbUser = prop.getProperty("db.user");
//        password = prop.getProperty("db.password");
//        driver = prop.getProperty("db.driver");
//    }
//
//    private BasicDataSource getDataSource() {
//        BasicDataSource ds = new BasicDataSource();
//        ds.setUrl(url);
//        ds.setUsername(dbUser);
//        ds.setPassword(password);
//        ds.setMinIdle(1);
//        ds.setMaxIdle(20);
//        ds.setDriverClassName(driver);
//
//        return ds;
//    }
//
//    public Connection createConnection() {
//
//        basicDataSource = getDataSource();
//        Connection connection = null;
////        try {
////            Class.forName(driver);
////        } catch (ClassNotFoundException e) {
////            e.printStackTrace();
////        }
//        try {
//            if (basicDataSource != null) {
//                connection = basicDataSource.getConnection();
//            }
//
//        } catch (SQLException ex) {
//            ex.printStackTrace();
//        }
//        return connection;
//    }
}
