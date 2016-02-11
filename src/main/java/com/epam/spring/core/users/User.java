package com.epam.spring.core.users;

import com.epam.spring.core.shared.BaseEntity;
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
}
