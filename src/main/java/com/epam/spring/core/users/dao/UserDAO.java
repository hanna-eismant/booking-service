package com.epam.spring.core.users.dao;

import com.epam.spring.core.users.User;

import java.util.List;

public interface UserDAO {
    User create(User user);

    void remove(User user);

    // TODO: need throw exception when is more than one user
    User findById(Long id);

    // TODO: need throw exception when is more than one user
    User findByEmail(String email);

    List<User> findByName(String name);
}
