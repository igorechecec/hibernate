package com.nixsolutions.dao;

import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.dbutils.DbUtils;

public class JdbcUserDao extends AbstractJdbcDao implements UserDao {

    /**
     * Default non-arg constructor.
     */
    public JdbcUserDao() {

    }

    /**
     * Add new user.
     *
     * @param user new user.
     */
    @Override
    public void create(User user) {
        PreparedStatement stm = null;
        try (Connection conn = createConnection()) {
            JdbcRoleDao roleDao = new JdbcRoleDao();
            Role role = roleDao.findByName(user.getRole().getName());
            conn.setAutoCommit(false);
            String sql = "INSERT INTO testdb.users(login, pass, email, "
                + "firstname, lastname, birthday, role_id) VALUES("
                + "?, ?, ?, ?, ?, ?, ?)";
            stm = conn.prepareStatement(sql);
            stm.setString(1, (String) isNull(user.getLogin()));
            stm.setString(2, (String) isNull(user.getPassword()));
            stm.setString(3, (String) isNull(user.getEmail()));
            stm.setString(4, (String) isNull(user.getFirstName()));
            stm.setString(5, (String) isNull(user.getLastName()));
            stm.setDate(6, (Date) isNull(user.getBirthday()));
            stm.setLong(7, (Long) isNull(role.getId()));

            stm.executeUpdate();

            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Update info about user.
     *
     * @param user updated user.
     */
    @Override
    public void update(User user) {
        PreparedStatement stm = null;
        try (Connection conn = createConnection()) {
            JdbcRoleDao roleDao = new JdbcRoleDao();
            Role role = roleDao.findByName(user.getRole().getName());

            conn.setAutoCommit(false);
            String sql = "UPDATE testdb.users SET login = ?, pass = ?, "
                + "email = ?, firstname = ?, lastname = ?, birthday = ?, "
                + "role_id = ? WHERE id = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, (String) isNull(user.getLogin()));
            stm.setString(2, (String) isNull(user.getPassword()));
            stm.setString(3, (String) isNull(user.getEmail()));
            stm.setString(4, (String) isNull(user.getFirstName()));
            stm.setString(5, (String) isNull(user.getLastName()));
            stm.setDate(6, (Date) isNull(user.getBirthday()));
            stm.setLong(7, role.getId());
            stm.setLong(8, user.getId());

            if (stm.executeUpdate() == 0) {
                throw new IllegalArgumentException("User with does not exist");
            }

            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Remove user.
     *
     * @param user removed user.
     */
    @Override
    public void remove(User user) {
        PreparedStatement stm = null;
        try (Connection conn = createConnection()) {
             conn.setAutoCommit(false);

             String sql = "DELETE FROM testdb.users WHERE id = ?";
             stm = conn.prepareStatement(sql);
             stm.setLong(1, user.getId());
             if (stm.executeUpdate() == 0) {
                 throw new IllegalArgumentException("Wrong argument or user"
                     + " is not exists");
             }

             conn.commit();
             conn.setAutoCommit(true);
         } catch (SQLException e) {
             e.printStackTrace();
         } finally {
            if (stm != null) {
                try {
                    stm.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Finds all available users.
     *
     * @return list of users.
     */
    @Override
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Statement stm = null;
        ResultSet usersSet = null;
        try (Connection conn = createConnection()) {
            conn.setAutoCommit(false);
            stm = conn.createStatement();
            usersSet = stm.executeQuery("SELECT * FROM testdb.users");
            while (usersSet.next()) {
                users.add(userMapper(usersSet));
            }

            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stm != null && usersSet != null) {
                try {
                    stm.close();
                    usersSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return users;
    }

    /**
     * Finds user by given login.
     *
     * @param login login by which searching user.
     * @return searched user.
     */
    @Override
    public User findByLogin(String login) {
        User user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet userSet = null;
        try {
            conn = createConnection();
            String sql = "SELECT * FROM testdb.users WHERE login = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, login);
            userSet = stm.executeQuery();
            if (userSet.next()) {
                user = userMapper(userSet);
            }
            DbUtils.commitAndCloseQuietly(conn);
        } catch (SQLException e) {
            //logger.error("Sql exception when findAll method executing", e);
            DbUtils.rollbackAndCloseQuietly(conn);
        } finally {
            DbUtils.closeQuietly(stm);
            DbUtils.closeQuietly(userSet);
        }
        return user;
    }

    public User findById(Long id) {
        User user = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet userSet = null;
        try {
            conn = createConnection();
            String sql = "SELECT * FROM testdb.users WHERE id = ?";
            stm = conn.prepareStatement(sql);
            stm.setLong(1, id);
            userSet = stm.executeQuery();
            if (userSet.next()) {
                user = userMapper(userSet);
            }
            DbUtils.commitAndCloseQuietly(conn);
        } catch (SQLException e) {
            //logger.error("Sql exception when findAll method executing", e);
            DbUtils.rollbackAndCloseQuietly(conn);
        } finally {
            DbUtils.closeQuietly(stm);
            DbUtils.closeQuietly(userSet);
        }
        return user;
    }

    /**
     * Finds user by given email.
     *
     * @param email email by which searching user.
     * @return searched user.
     */
    @Override
    public User findByEmail(String email) {
        User user = null;
        PreparedStatement stm = null;
        ResultSet userSet = null;
        try (Connection conn = createConnection()) {

            conn.setAutoCommit(false);
            String sql = "SELECT * FROM testdb.users WHERE email = ?";
            stm = conn.prepareStatement(sql);
            stm.setString(1, email);
            userSet = stm.executeQuery();
            if (userSet.next()) {
                user = userMapper(userSet);
            } else {
                throw new IllegalArgumentException("User with given email"
                    + "is not exist");
            }

            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stm != null && userSet != null) {
                try {
                    stm.close();
                    userSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return user;
    }

    /**
     * Checks param of entity for null equality.
     *
     * @param param of entity.
     * @return param or exception if param = null.
     * @throws IllegalArgumentException if param is null.
     */
    private Object isNull(Object param) {
        if (param == null) {
            throw new IllegalArgumentException();
        }
        return param;
    }

    /**
     * Maps field of result set to entity.
     *
     * @param resultSet fields with data.
     * @return initialized user.
     * @throws SQLException
     */
    private User userMapper(ResultSet resultSet) throws SQLException {
        User user = new User();
        JdbcRoleDao roleDao = new JdbcRoleDao();
        Role role = roleDao.findById(resultSet.getLong("role_id"));
        user.setId(resultSet.getLong("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("pass"));
        user.setEmail(resultSet.getString("email"));
        user.setFirstName(resultSet.getString("firstname"));
        user.setLastName(resultSet.getString("lastname"));
        user.setBirthday(resultSet.getDate("birthday"));
        user.setRole(role);
        return user;
    }
}
