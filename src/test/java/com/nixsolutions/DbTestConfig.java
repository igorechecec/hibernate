package com.nixsolutions;

import java.io.IOException;
import java.util.Properties;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbTestConfig {

    private static Logger logger = LoggerFactory.getLogger(DbTestConfig.class);
    protected CustomTester tester;
    private Properties prop;
    protected IDataSet beforeData;

    @Before
    public void setUp() throws Exception {
        tester = new CustomTester(prop.getProperty("db.driver"),
            prop.getProperty("db.url"),
            prop.getProperty("db.user"),
            prop.getProperty("db.password"),
            "testdb");

    }

    public DbTestConfig() {
        prop = new Properties();

        try {
            prop.load(getClass().getClassLoader().
                getResourceAsStream("database.properties"));

        } catch (IOException e) {
            logger.error("Can't load properties", e);
            throw new RuntimeException(e);
        }

        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
            prop.getProperty("db.driver"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
            prop.getProperty("db.url"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
            prop.getProperty("db.user"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
            prop.getProperty("db.password"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA,
            "testdb");

    }

    protected IDataSet getDataSet() throws Exception {
        return beforeData;
    }
}
