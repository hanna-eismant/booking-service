package com.epam.spring.core.users;

import com.epam.spring.core.shared.BaseDAO;

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
     * Find registered user with specify name.
     *
     * @param name user name for search.
     * @return found user or {@code null}.
     * @throws IllegalArgumentException if passed name is {@code null}.
     */
    User findByName(String name) throws IllegalArgumentException;
}
