package com.nixsolutions.dao;

import com.nixsolutions.entity.Role;

public interface RoleDao {

    /**
     * Creates new role.
     *
     * @param role new role.
     */
    void create(Role role);

    /**
     * Updates role info.
     *
     * @param role updated role.
     */
    void update(Role role);

    /**
     * Removes role.
     *
     * @param role removed role.
     */
    void remove(Role role);

    /**
     * Search role by given name.
     *
     * @param name name of role.
     * @return searched role.
     */
    Role findByName(String name);
}
