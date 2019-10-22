package com.nixsolutions.test;

import com.nixsolutions.dao.HibernateRoleDao;
import com.nixsolutions.entity.Role;
import org.dbunit.PrepAndExpectedTestCase;
import org.dbunit.PrepAndExpectedTestCaseSteps;
import org.dbunit.VerifyTableDefinition;
import org.junit.Test;

public class HibernateRoleDaoTest extends DbUnitConfig{
    private static final String TABLE_NAME = "role";
    private static final VerifyTableDefinition[] TABLES =
        {new VerifyTableDefinition(TABLE_NAME, new String[]{})};

    private HibernateRoleDao roleDao;

    public HibernateRoleDaoTest() {
        super();
        roleDao = new HibernateRoleDao();
    }

    @Test
    public void testCreateRole() throws Exception {
        final String[] expectedDataFiles = {"entity.Role/role-create-data.xml"};
        final PrepAndExpectedTestCaseSteps testSteps = () -> {
            Role admin = new Role();
            admin.setName("Admin");
            roleDao.create(admin);
            return null;
        };
        prepAndExpectedTestCase.runTest(TABLES, PREP_DATA_FILES, expectedDataFiles, testSteps);
    }
}
