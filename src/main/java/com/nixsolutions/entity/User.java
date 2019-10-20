package com.nixsolutions.entity;

import java.sql.Date;
import java.util.Objects;

public class User {

    /**
     * Default non-arg constructor.
     */
    public User() {

    }

    /**
     * User's id.
     */
    private long id;

    /**
     * Login of user.
     */
    private String login;

    /**
     * Password of user.
     */
    private String password;

    /**
     * Email of user.
     */
    private String email;

    /**
     * Firstname of user.
     */
    private String firstName;

    /**
     * Lastname of user.
     */
    private String lastName;

    /**
     * Birthday of user.
     */
    private Date birthday;

    /**
     * User's role.
     */
    private Role role;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id
            && Objects.equals(login, user.login)
            && Objects.equals(password, user.password)
            && Objects.equals(email, user.email)
            && Objects.equals(firstName, user.firstName)
            && Objects.equals(lastName, user.lastName)
            && Objects.equals(birthday, user.birthday)
            && Objects.equals(role, user.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, login, password, email,
            firstName, lastName, birthday, role);
    }
}
