package com.epam.spring.core.users.dao;

import com.epam.spring.core.shared.BaseDAO;
import com.epam.spring.core.users.User;

import java.util.List;

public interface UserDAO extends BaseDAO<User> {

    /**
     * Find registered user with specify e-mail.
     *
     * @param email user e-mail for search.
     * @return found user or {@code null}.
     * @throws IllegalArgumentException if passed email is {@code null}.
     */
    User findByEmail(String email) throws IllegalArgumentException;

    /**
     * Find registered users with specify name.
     *
     * @param name user name for search.
     * @return list with found users or empty list.
     * @throws IllegalArgumentException if passed name is {@code null}.
     */
    List<User> findByName(String name) throws IllegalArgumentException;
}
