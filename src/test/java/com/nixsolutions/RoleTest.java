package com.nixsolutions;

import com.nixsolutions.dao.HibernateRoleDao;
import com.nixsolutions.entity.Role;
import com.nixsolutions.utils.HibernateUtils;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RoleTest extends DbTestConfig {

    private HibernateRoleDao roleDao;
    private IDataSet expectedData;
    private final String TABLE = "role";

    @Before
    public void setUp() throws Exception {
        super.setUp();
        roleDao = new HibernateRoleDao();
        beforeData = new FlatXmlDataSetBuilder()
            .build(Thread.currentThread().getContextClassLoader()
                .getResourceAsStream("entity.Role/role-data.xml"));
        tester.setDataSet(beforeData);
        tester.onSetup();

    }

    @After
    public void tearDown() throws Exception{
        super.tearDown();
    }

    @Test
    public void testCreateRole() throws Exception {
        Role role  = new Role();
        role.setName("guest");
        roleDao.create(role);

        expectedData = new FlatXmlDataSetBuilder()
            .setCaseSensitiveTableNames(true).build(
                getClass().getClassLoader()
                    .getResourceAsStream("entity.Role/role-create-data.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();
        Assertion.assertEquals(expectedData.getTable(TABLE), actualData.getTable(TABLE));
    }

    @Test
    public void testUpdateRole() throws Exception {
        Role role = new Role();
        role.setId(2);
        role.setName("guest");
        roleDao.update(role);

        expectedData = new FlatXmlDataSetBuilder()
            .setCaseSensitiveTableNames(true).build(
                getClass().getClassLoader()
                    .getResourceAsStream("entity.Role/role-update-data.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();
        String[] ignore = {"id"};
        Assertion.assertEqualsIgnoreCols(expectedData.getTable(TABLE), actualData.getTable(TABLE), ignore);

    }

    @Test
    public void testRemoveRole() throws Exception {
        Role role = new Role();
        role.setId(1);
        role.setName("user");
        roleDao.remove(role);

        expectedData = new FlatXmlDataSetBuilder()
            .setCaseSensitiveTableNames(true).build(
                getClass().getClassLoader()
                    .getResourceAsStream("entity.Role/role-remove-data.xml"));

        IDataSet actualData = tester.getConnection().createDataSet();
        Assertion.assertEquals(expectedData.getTable(TABLE), actualData.getTable(TABLE));
    }

    @Test
    public void testFindRoleByName() throws Exception {
        Role actualRole;
        Role expectedRole = new Role();
        actualRole = roleDao.findByName("admin");
        ITable table = getDataSet().getTable(TABLE);
        expectedRole.setId(Long.parseLong((String) table.getValue(1, "id")));
        expectedRole.setName((String) table.getValue(1, "name"));
        Assert.assertEquals(expectedRole, actualRole);
    }
}
