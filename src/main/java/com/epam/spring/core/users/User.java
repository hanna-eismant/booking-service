package com.epam.spring.core.users;

import com.epam.spring.core.shared.BaseEntity;
import com.google.common.base.Objects;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

public class User extends BaseEntity {

    private String name;
    private String password;
    private String email;
    private LocalDate birthday;
    private List<UserRoles> roles = new ArrayList<>(1);

    public User() {
    }

    public User(final String _name, final String _email, final LocalDate _birthday) {
        name = _name;
        email = _email;
        birthday = _birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(final String _name) {
        name = _name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String _email) {
        email = _email;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(final LocalDate _birthday) {
        birthday = _birthday;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String _password) {
        password = _password;
    }

    public List<UserRoles> getRoles() {
        return roles;
    }

    @Override
    public boolean equals(final Object _o) {
        if (this == _o) return true;
        if (_o == null || getClass() != _o.getClass()) return false;
        User user = (User) _o;
        return Objects.equal(getName(), user.getName()) &&
                Objects.equal(getPassword(), user.getPassword()) &&
                Objects.equal(getEmail(), user.getEmail()) &&
                Objects.equal(getBirthday(), user.getBirthday()) &&
                Objects.equal(getRoles(), user.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName(), getPassword(), getEmail(), getBirthday(), getRoles());
    }
}
