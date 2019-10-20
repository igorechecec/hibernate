package com.nixsolutions.entity;

import java.util.Objects;

public class Role {

    /**
     * Default non-arg constructor.
     */
    public Role() {

    }

    /**
     * Id of field.
     */
    private long id;

    /**
     * Name of role.
     */
    private String name;

    /**
     * Sets id of role.
     *
     * @param id role's id.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets id of role.
     *
     * @return role's id.
     */
    public long getId() {
        return id;
    }

    /**
     * Gets name of role.
     *
     * @return role's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name of role.
     *
     * @param name role's name.
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return id == role.id &&
            Objects.equals(name, role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
