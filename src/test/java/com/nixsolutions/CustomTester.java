package com.nixsolutions;

import org.dbunit.JdbcDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.database.IDatabaseConnection;

public class CustomTester extends JdbcDatabaseTester {

    public CustomTester(String driverClass, String connectionUrl, String username, String password, String schema) throws ClassNotFoundException {
        super(driverClass, connectionUrl, username, password, schema);
    }

    @Override
    public IDatabaseConnection getConnection() throws Exception{
        IDatabaseConnection connection = super.getConnection();
        DatabaseConfig config = connection.getConfig();
        config.setProperty(DatabaseConfig.FEATURE_QUALIFIED_TABLE_NAMES, true);
        config.setProperty(DatabaseConfig.FEATURE_CASE_SENSITIVE_TABLE_NAMES, true);
        return connection;
    }
}
