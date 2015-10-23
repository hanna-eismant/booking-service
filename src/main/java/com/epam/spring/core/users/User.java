package com.epam.spring.core.users;

import java.util.Date;

public class User {

    public Long id;
    public String name;
    public String email;
    public Date birthday;

    public User() {
    }

    public User(String name, String email, Date birthday) {
        this.name = name;
        this.email = email;
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                '}';
    }
}
