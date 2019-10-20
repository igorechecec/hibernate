package com.nixsolutions;


import com.github.database.rider.core.DBUnitRule;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.core.api.dataset.ExpectedDataSet;
import com.nixsolutions.dao.JdbcRoleDao;
import com.nixsolutions.entity.Role;
import java.sql.Connection;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
@DBUnit(url = "jdbc:h2:mem:testdb:;INIT=RUNSCRIPT FROM 'classpath:data/init.sql';DB_CLOSE_DELAY=-1;DATABASE_TO_UPPER=false",
    driver = "org.h2.Driver", user = "sa", password = "sa",
    caseSensitiveTableNames = true, qualifiedTableNames = true)
@DataSet(value = "entity.Role/role-data.xml")
public class DbRoleTest {

    private JdbcRoleDao roleDao = new JdbcRoleDao();

    @Rule
    public DBUnitRule dbUnitRule = DBUnitRule.instance();

    @Test
    @ExpectedDataSet(value = "entity.Role/role-create-data.xml")
    public void createRoleTest() throws Exception {
        Role role = new Role();
        try {
            roleDao.create(role);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e){
        }
        role.setName("guest");
        roleDao.create(role);
    }

    @Test
    @ExpectedDataSet(value = "entity.Role/role-update-data.xml")
    public void updateRoleTest() throws Exception {
        Role role = new Role();
        try {
            roleDao.update(role);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e){
        }
        role.setId(2);
        role.setName("guest");
        roleDao.update(role);
    }

    @Test
    @ExpectedDataSet(value = "entity.Role/role-remove-data.xml")
    public void removeRoleTest() throws Exception {
        Role role = new Role();
        try {
            roleDao.remove(role);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e){
        }
        role.setId(1);
        role.setName("user");
        roleDao.remove(role);

    }

    @Test
    public void findByNameRoleTest() throws Exception {
        try {

            roleDao.findByName("ji");
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e){
        }
        Role role = roleDao.findByName("admin");
        assertThat(role, is(notNullValue()));
        assertThat(role.getId(), is((long) 2));
        assertThat(role.getName(), is("admin"));

    }
}
