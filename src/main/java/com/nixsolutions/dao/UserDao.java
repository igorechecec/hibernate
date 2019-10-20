package com.nixsolutions.dao;

import com.nixsolutions.entity.User;
import java.util.List;

public interface UserDao {

    /**
     * Add new user.
     *
     * @param user new user.
     */
    void create(User user);

    /**
     * Update info about user.
     *
     * @param user updated user.
     */
    void update(User user);

    /**
     * Remove user.
     *
     * @param user removed user.
     */
    void remove(User user);

    /**
     * Finds all available users.
     *
     * @return list of users.
     */
    List<User> findAll();

    /**
     * Finds user by given login.
     *
     * @param login login by which searching user.
     * @return searched user.
     */
    User findByLogin(String login);

    /**
     * Finds user by given email.
     *
     * @param email email by which searching user.
     * @return searched user.
     */
    User findByEmail(String email);
}
