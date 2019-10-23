package com.nixsolutions;

import com.nixsolutions.dao.HibernateRoleDao;
import com.nixsolutions.dao.HibernateUserDao;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;
import java.sql.Date;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        HibernateRoleDao roleDao = new HibernateRoleDao();
        HibernateUserDao userDao = new HibernateUserDao();
        Role role = new Role();
        User user = new User();
        role.setName("Admin");
        roleDao.create(role);
        role.setName("User");
        roleDao.create(role);
        role = roleDao.findByName("Admin");

        user.setLogin("Velich");
        user.setEmail("12@h.j");
        user.setPassword("password");
        user.setFirstName("Igor");
        user.setLastName("Velychko");
        LocalDate date = LocalDate.now();
        user.setBirthday(Date.valueOf(date));
        user.setRole(role);

        userDao.create(user);

        System.out.println(userDao.findByLogin("Velich").getLastName());
    }
}
