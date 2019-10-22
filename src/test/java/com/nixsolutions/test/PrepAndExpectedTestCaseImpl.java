package com.nixsolutions.test;

import org.dbunit.DefaultPrepAndExpectedTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.database.DatabaseConfig;
import org.dbunit.ext.h2.H2DataTypeFactory;
import org.dbunit.util.fileloader.DataFileLoader;

public class PrepAndExpectedTestCaseImpl extends DefaultPrepAndExpectedTestCase {

    public PrepAndExpectedTestCaseImpl() {
    }

    public PrepAndExpectedTestCaseImpl(DataFileLoader dataFileLoader,
                                       IDatabaseTester databaseTester) {
        super(dataFileLoader, databaseTester);
    }

    protected void setUpDatabaseConfig(final DatabaseConfig config) {
//        config.setProperty(DatabaseConfig.PROPERTY_ALLOW_VERIFYTABLEDEFINITION_EXPECTEDTABLE_COUNT_MISMATCH,
//            true);
        config.setProperty(DatabaseConfig.PROPERTY_DATATYPE_FACTORY,
            new H2DataTypeFactory());
    }
}
