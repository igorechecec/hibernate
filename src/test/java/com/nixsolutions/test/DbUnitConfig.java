package com.nixsolutions.test;

import com.nixsolutions.utils.HibernateUtils;
import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.DatabaseUnitRuntimeException;
import org.dbunit.DefaultPrepAndExpectedTestCase;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.util.fileloader.FlatXmlDataFileLoader;

public class DbUnitConfig {
    protected static final String[] PREP_DATA_FILES = {"entity.Role/role-data.xml"};

    protected DefaultPrepAndExpectedTestCase prepAndExpectedTestCase;

    public DbUnitConfig() {
        prepAndExpectedTestCase =
            new PrepAndExpectedTestCaseImpl(new DataLoader(),
                new DataSourceDatabaseTester(HibernateUtils
                    .getDataSource()));
    }

    public class DataLoader extends FlatXmlDataFileLoader {
        @Override
        public IDataSet load(String filename)
            throws DatabaseUnitRuntimeException {
            try {
                return new FlatXmlDataSetBuilder()
                    .build(DbUnitConfig.class.getClassLoader()
                        .getResourceAsStream(filename));
            } catch (DataSetException e) {
                throw new RuntimeException("Cannot load dataset", e);
            }
        }
    }
}
