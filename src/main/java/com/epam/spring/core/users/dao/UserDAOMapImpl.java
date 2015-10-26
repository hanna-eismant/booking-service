package com.epam.spring.core.users.dao;

import com.epam.spring.core.Util;
import com.epam.spring.core.users.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserDAOMapImpl implements UserDAO {

    private static Map<Long, User> users = new HashMap<>();

    @Override
    public User create(User user) {
        // todo: check for name, email and birthday

        Long id;
        do {
            id = Util.generateId();
        } while (users.containsKey(id));

        user.id = id;
        users.put(id, user);
        return user;
    }

    @Override
    public void remove(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be 'null'");
        }

        // todo: check for user.id

        if (users.containsKey(user.id)) {
            users.remove(user.id);
        }
    }

    @Override
    public User findById(Long id) {

        // todo: check for id
        if (users.containsKey(id)) {
            return users.get(id);
        } else {
            return null;
        }
    }

    @Override
    public User findByEmail(String email) throws IllegalArgumentException {
        if (email == null) {
            throw new IllegalArgumentException("Email for search cannot be 'null'");
        }

        for (User user : users.values()) {
            if (email.equals(user.email)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public List<User> findByName(String name) throws IllegalArgumentException {
        if (name == null) {
            throw new IllegalArgumentException("Name for search cannot be 'null'");
        }

        List<User> result = new ArrayList<>();

        for (User user : users.values()) {
            if (name.equals(user.name)) {
                result.add(user);
            }
        }

        return result;
    }
}
