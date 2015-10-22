package com.epam.spring.core.users.dao;

import com.epam.spring.core.users.User;

import java.util.List;

public interface UserDAO {
    User create(User user);

    void remove(User user);

    User findById(Long id);

    User findByEmail(String email);

    List<User> findByName(String name);
}
