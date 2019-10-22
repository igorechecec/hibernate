package com.nixsolutions;

import com.nixsolutions.dao.HibernateRoleDao;
import com.nixsolutions.dao.HibernateUserDao;
import com.nixsolutions.dao.JdbcUserDao;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.dbunit.Assertion;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UserTest extends DbTestConfig {

    private HibernateUserDao userDao = new HibernateUserDao();
    private IDataSet expectedData;
    private final String TABLE = "testdb.users";

    @Before
    public void setUp() throws Exception {
        super.setUp();

        beforeData = new FlatXmlDataSetBuilder().build(getClass()
            .getClassLoader().getResourceAsStream("entity.User/user-data.xml"));
        tester.setDataSet(beforeData);
        tester.onSetup();
    }

    @After
    public void getTearDownOperation() throws Exception {
        DatabaseOperation.CLEAN_INSERT.execute(tester.getConnection(), beforeData);
    }

    @Test
    public void testCreateUser() throws Exception {
        User user = new User();
        Role role = new Role();
        //role.setName("admin");
        Object[] param = {(long) 2, "bill@booker", "12345", "bill.booker@gmail.com",
            "bille", "booker", Date.valueOf("1996-01-20"), role};

        user = createExpectedUser(param);
        userDao.create(user);

        IDataSet actualData = tester.getConnection().createDataSet();
        expectedData = new FlatXmlDataSetBuilder()
            .setCaseSensitiveTableNames(true).build(
                getClass().getClassLoader()
                    .getResourceAsStream("entity.User/user-create-data.xml"));

        Assertion.assertEquals(expectedData.getTable(TABLE), actualData.getTable(TABLE));
    }

    @Test
    public void testUpdateUser() throws Exception {
        User user = new User();
        Role role = new Role();
        role.setId(2);
        role.setName("admin");
        Object[] param = {(long) 2, "bill@booker", "12345", "bill.booker@gmail.com",
            "bille", "booker", Date.valueOf("1996-01-20"), role};
        user = createExpectedUser(param);
        userDao.update(user);

        IDataSet actualData = tester.getConnection().createDataSet();
        expectedData = new FlatXmlDataSetBuilder()
            .setCaseSensitiveTableNames(true).build(
                getClass().getClassLoader()
                    .getResourceAsStream("entity.User/user-update-data.xml"));

        Assertion.assertEquals(expectedData.getTable(TABLE), actualData.getTable(TABLE));
    }

    @Test
    public void testRemoveUser() throws Exception {
        User user = new User();
        user.setId(2);

        userDao.remove(user);

        IDataSet actualData = tester.getConnection().createDataSet();
        expectedData = new FlatXmlDataSetBuilder()
            .setCaseSensitiveTableNames(true).build(
                getClass().getClassLoader()
                    .getResourceAsStream("entity.User/user-remove-data.xml"));

        Assertion.assertEquals(expectedData.getTable(TABLE), actualData.getTable(TABLE));
    }

    @Test
    public void testFindAll() throws Exception {
        Role role = new Role();
        role.setId(1);
        role.setName("user");
        Role role1 = new Role();
        role1.setId(2);
        role1.setName("admin");
        HibernateRoleDao roleDao = new HibernateRoleDao();
        roleDao.create(role);
        roleDao.create(role1);
        Object[] params = {(long) 1, "john@smith", "1234", "john.smith@gmail.com",
            "john", "smith", Date.valueOf("1999-01-01"), role};
        Object[] params1 = {(long) 2, "sara@millton", "abcd", "sara.millton@gmail.com",
            "sara", "millton", Date.valueOf("1985-01-01"), role1};
        List<User> expectedUsers = getListOfExpectedUsers();
        userDao.create(createExpectedUser(params));
        userDao.create(createExpectedUser(params1));
        List<User> actualUsers = userDao.findAll();

        Assert.assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testFindByLogin() throws Exception {
        Role role = new Role();
        role.setId(2);
        role.setName("admin");
        Object[] params = {(long) 2, "sara@millton", "abcd", "sara.millton@gmail.com",
            "sara", "millton", Date.valueOf("1985-01-01"), role};
        User expectedUser = createExpectedUser(params);
        User actualUser = userDao.findByLogin("sara@millton");
        Assert.assertEquals(expectedUser, actualUser);
    }

    private User createExpectedUser(Object[] param) {
        User user = new User();
        user.setId((Long) param[0]);
        user.setLogin((String) param[1]);
        user.setPassword((String) param[2]);
        user.setEmail((String) param[3]);
        user.setFirstName((String) param[4]);
        user.setLastName((String) param[5]);
        user.setBirthday((Date) param[6]);
        user.setRole((Role) param[7]);
        return user;
    }

    private List<User> getListOfExpectedUsers() {
        Role role = new Role();
        role.setId(1);
        role.setName("user");
        Role role1 = new Role();
        role1.setId(2);
        role1.setName("admin");
        Object[] params = {(long) 1, "john@smith", "1234", "john.smith@gmail.com",
            "john", "smith", Date.valueOf("1999-01-01"), role};
        Object[] params1 = {(long) 2, "sara@millton", "abcd", "sara.millton@gmail.com",
            "sara", "millton", Date.valueOf("1985-01-01"), role1};
        List<User> users = new ArrayList<>();
        User user = createExpectedUser(params);
        User user1 = createExpectedUser(params1);
        users.add(user);
        users.add(user1);
        return users;
    }
}
