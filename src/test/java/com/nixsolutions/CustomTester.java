package com.nixsolutions;

import java.sql.Connection;
import java.sql.DriverManager;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.ext.h2.H2Connection;
import org.dbunit.ext.h2.H2DataTypeFactory;

public class CustomTester extends JdbcDatabaseTester {

    public CustomTester(String driverClass, String connectionUrl, String username, String password, String schema) throws ClassNotFoundException {
        super(driverClass, connectionUrl, username, password, schema);
    }
    public CustomTester(String driverClass, String connectionUrl, String username, String password) throws ClassNotFoundException {
        super(driverClass, connectionUrl, username, password);
    }

    @Override
    public IDatabaseConnection getConnection() throws Exception{
        IDatabaseConnection connection = super.getConnection();
        DatabaseConfig config = connection.getConfig();
        config.setProperty(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, true);
        config.setProperty(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, true);
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY, new H2DataTypeFactory());
        return connection;
    }
}
