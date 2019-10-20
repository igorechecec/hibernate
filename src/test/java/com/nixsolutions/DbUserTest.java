package com.nixsolutions;

import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.nixsolutions.dao.JdbcUserDao;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@DBUnit(caseSensitiveTableNames = true, qualifiedTableNames = true)
@DataSet(value = "entity.User/user-data.xml")
public class DbUserTest {

    private JdbcUserDao userDao = new JdbcUserDao();
    private Connection conn = userDao.createConnection();

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance(conn);

    @Test
    @ExpectedDataSet(value = "entity.User/user-create-data.xml")
    public void createUserTest() throws Exception {
        User user;
        Role role = new Role();
        Object[] param = {(long) 2, "bill@booker", "12345", "bill.booker@gmail.com",
            "bille", "booker", Date.valueOf("1996-01-20"), role};
        user = createExpectedUser(param);
        try {
            role.setName("a");
            userDao.create(user);
            fail("Role must be exist!");
        } catch (IllegalArgumentException ex) {
        }

        try {
            role.setName("admin");
            Object[] wrongParams = {(long) 2, "bill@booker", null, "bill.booker@gmail.com",
                "bille", "booker", Date.valueOf("1996-01-20"), role};
            user = createExpectedUser(wrongParams);
            userDao.create(user);
            fail("Should throws IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
        user = createExpectedUser(param);
        role.setName("admin");
        userDao.create(user);
    }

    @Test
    @ExpectedDataSet(value = "entity.User/user-update-data.xml")
    public void updateUserTest() {
        User user;
        Role role = new Role();;
        role.setName("admin");
        Object[] param = {(long) 2, "bill@booker", "12345", "bill.booker@gmail.com",
            "bille", "booker", Date.valueOf("1996-01-20"), role};
        user = createExpectedUser(param);
        try {
            role.setName("a");
            userDao.update(user);
            fail("Role must be exist!");
        } catch (IllegalArgumentException ex) {
        }

        try {
            role.setName("admin");
            Object[] wrongParams = {(long) 2, "bill@booker", null, "bill.booker@gmail.com",
                "bille", "booker", Date.valueOf("1996-01-20"), role};
            user = createExpectedUser(wrongParams);
            userDao.update(user);
            fail("Should throws IllegalArgumentException");
        } catch (IllegalArgumentException e) {
        }
        user = createExpectedUser(param);
        role.setName("admin");
        userDao.update(user);
    }

    @Test
    @ExpectedDataSet(value = "entity.User/user-remove-data.xml")
    public void removeUserTest() throws Exception {
        User user = new User();
        try {
            user.setId(10);
            userDao.remove(user);
            fail("Should throws IllegalArgumentException!");
        } catch (IllegalArgumentException e) {
        }
        user.setId(2);
        userDao.remove(user);
    }

    @Test
    public void findAllTest() throws Exception {
        List<User> expectedUsers = getListOfExpectedUsers();
        List<User> actualUsers = userDao.findAll();

        assertEquals(expectedUsers, actualUsers);
    }

//    @Test
//    public void findByLoginTest() throws Exception {
//        Role role = new Role();
//        try {
//            userDao.findByLogin("wrong login");
//            fail("Should throws exception");
//        } catch (IllegalArgumentException e) {
//        }
//
//        role.setId(2);
//        role.setName("admin");
//        Object[] params = {(long) 2, "sara@millton", "abcd", "sara.millton@gmail.com",
//            "sara", "millton", Date.valueOf("1985-01-01"), role};
//        User expectedUser = createExpectedUser(params);
//        User actualUser = userDao.findByLogin("sara@millton");
//        assertEquals(expectedUser, actualUser);
//    }

    @Test
    public void findByEmailTest() throws Exception {
        Role role = new Role();
        try {
            userDao.findByEmail("wrong email");
            fail("Should throws exception");
        } catch (IllegalArgumentException e) {
        }

        role.setId(1);
        role.setName("user");
        Object[] params = {(long) 1, "john@smith", "1234", "john.smith@gmail.com",
            "john", "smith", Date.valueOf("1999-01-01"), role};
        User expectedUser = createExpectedUser(params);
        User actualUser = userDao.findByEmail("john.smith@gmail.com");
        assertEquals(expectedUser, actualUser);
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
