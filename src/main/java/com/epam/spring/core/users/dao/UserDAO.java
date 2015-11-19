package com.epam.spring.core.users.dao;

import com.epam.spring.core.BaseDAO;
import com.epam.spring.core.users.User;

import java.util.List;

public interface UserDAO extends BaseDAO<User> {

//    /**
//     * Register new user to system.
//     *
//     * @param user user object with filled fields exclude {@link User#id}.
//     * @return registered user with ID.
//     * @throws IllegalArgumentException if passed user is {@code null}.
//     */
//    User create(User user) throws IllegalArgumentException;
//
//    /**
//     * Remove passed user from system. If user is absent, then do nothing.
//     *
//     * @param user user to remove.
//     * @throws IllegalArgumentException if passed user or it id is {@code null}.
//     */
//    void remove(User user) throws IllegalArgumentException;
//
//    /**
//     * Find registered user with specify ID.
//     *
//     * @param id user id for search.
//     * @return found user or {@code null}.
//     * @throws IllegalArgumentException if passed id is {@code null}.
//     */
//    User findById(Long id) throws IllegalArgumentException;

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

    /**
     * Find all registered users.
     *
     * @return list of all registered users or empty list.
     */
    List<User> findAll();

    /**
     * Remove all users from Booking system.
     */
    void removeAll();
}
