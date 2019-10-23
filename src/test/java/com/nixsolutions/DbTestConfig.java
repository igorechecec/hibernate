package com.nixsolutions;

import java.util.Properties;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DbTestConfig {

    private static Logger logger = LoggerFactory.getLogger(DbTestConfig.class);
    protected IDatabaseTester tester;
    private Properties prop;
    protected IDataSet beforeData;

    @Before
    public void setUp() throws Exception {
        tester = new JdbcDatabaseTester(prop.getProperty("connection.driver_class"),
            prop.getProperty("connection.url"),
            prop.getProperty("connection.username"),
            prop.getProperty("connection.password")) {
            @Override
            public IDatabaseConnection getConnection() throws Exception {
                IDatabaseConnection connection = super.getConnection();
                connection.getConfig().setProperty(
                    DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
                return connection;
            }
        };

    }

    public DbTestConfig() {
//        try {
//            prop.load(getClass().getClassLoader().
//                getResourceAsStream("database.properties"));
//
//        } catch (IOException e) {
//            logger.error("Can't load properties", e);
//            throw new RuntimeException(e);
//        }
        Configuration configuration = new Configuration().configure();
        prop = configuration.getProperties();
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS,
            prop.getProperty("connection.driver_class"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL,
            prop.getProperty("connection.url"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME,
            prop.getProperty("connection.username"));
        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD,
            prop.getProperty("connection.password"));
//        System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_SCHEMA,
//            "testdb");

    }

    protected IDataSet getDataSet() throws Exception {
        return beforeData;
    }

    public void tearDown() throws Exception {
        tester.onTearDown();
        tester.getConnection().close();
    }
}
