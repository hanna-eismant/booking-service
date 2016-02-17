package com.epam.spring.core.users;

import com.epam.spring.core.shared.BaseEntity;
import com.google.common.base.Objects;
import org.joda.time.LocalDate;

public class User extends BaseEntity {

    private String name;
    private String email;
    private LocalDate birthday;

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

    @Override
    public boolean equals(final Object _o) {
        if (this == _o) return true;
        if (_o == null || getClass() != _o.getClass()) return false;
        User user = (User) _o;
        return Objects.equal(getId(), user.getId()) &&
                Objects.equal(getName(), user.getName()) &&
                Objects.equal(getEmail(), user.getEmail()) &&
                Objects.equal(getBirthday(), user.getBirthday());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId(), getName(), getEmail(), getBirthday());
    }
}
