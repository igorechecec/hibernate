package com.nixsolutions.dao;

import com.nixsolutions.entity.Role;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcRoleDao extends AbstractJdbcDao implements RoleDao {

    /**
     * Default non-arg constructor.
     */
    public JdbcRoleDao() {

    }

    /**
     * Creates new role.
     *
     * @param role new role.
     */
    @Override
    public void create(Role role) {
        if (role == null) {
            throw new NullPointerException("Role shouldn't equal to null");
        }
        if (role.getName() == null) {
            throw new IllegalArgumentException("Role shouldn't "
                + "have empty name");
        }
        PreparedStatement stm = null;
        try (Connection conn = createConnection()) {

            conn.setAutoCommit(false);
            stm = conn.prepareStatement("INSERT INTO "
                + "testdb.roles(name) VALUES ?");
            stm.setString(1, role.getName());

            if (stm.executeUpdate() == 0) {
                throw new IllegalArgumentException();
            }
            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException ex) {
            ex.printStackTrace();
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
     * Updates role info.
     *
     * @param role updated role.
     */
    @Override
    public void update(Role role) {
        if (role == null) {
            throw new NullPointerException("Role shouldn't equal to null");
        }
        if (role.getName() == null || role.getId() == 0) {
            throw new IllegalArgumentException("Role shouldn't "
                + "have empty name");
        }
        PreparedStatement stm = null;
        try (Connection conn = createConnection()) {

            conn.setAutoCommit(false);
            stm = conn.prepareStatement("UPDATE "
                + "testdb.roles SET name = ? WHERE id = ?");

            stm.setString(1, role.getName());
            stm.setLong(2, role.getId());

            if (stm.executeUpdate() == 0) {
                throw new IllegalArgumentException();
            }

            conn.commit();
            conn.setAutoCommit(true);

        } catch (SQLException ex) {
            ex.printStackTrace();
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
     * Removes role.
     *
     * @param role removed role.
     */
    @Override
    public void remove(Role role) {
        if (role == null) {
            throw new NullPointerException("Role shouldn't equal to null");
        }
        if (role.getName() == null || role.getId() == 0) {
            throw new IllegalArgumentException("Role shouldn't "
                + "have empty name");
        }
        PreparedStatement stm = null;
        try (Connection conn = createConnection()) {
            conn.setAutoCommit(false);
            stm = conn.prepareStatement("DELETE FROM "
                + "testdb.roles WHERE id = ?");
            stm.setLong(1, role.getId());
            stm.execute();

            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
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
     * Search role by given name.
     *
     * @param name name of role.
     * @return searched role.
     */
    @Override
    public Role findByName(String name) {
        if (name == null) {
            throw new NullPointerException("Name shouldn't equal to null");
        }
        Role role = new Role();
        PreparedStatement stm = null;
        ResultSet roleSet = null;
        try (Connection conn = createConnection()) {
            conn.setAutoCommit(false);
            stm = conn.prepareStatement("SELECT * FROM "
                + "testdb.roles WHERE name = ?");
            stm.setString(1, name);
            roleSet = stm.executeQuery();
            if (roleSet.next()) {
                role.setId(roleSet.getLong("id"));
                role.setName(roleSet.getString("name"));
            } else {
                throw new IllegalArgumentException("Role shouldn't "
                    + "have empty name");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (stm != null && roleSet != null) {
                try {
                    stm.close();
                    roleSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return role;
    }

    protected Role findById(long id) {
        Role role = new Role();
        PreparedStatement stm = null;
        ResultSet roleSet = null;
        try (Connection conn = createConnection()) {
            conn.setAutoCommit(false);
            stm = conn.prepareStatement("SELECT * "
                + "FROM testdb.roles WHERE id = ?");
            stm.setLong(1, id);
            roleSet = stm.executeQuery();
            if (roleSet.next()) {
                role.setId(id);
                role.setName(roleSet.getString("name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stm != null && roleSet != null) {
                try {
                    stm.close();
                    roleSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return role;
    }
}
