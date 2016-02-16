package com.epam.spring.core.users;

import com.epam.spring.core.shared.DuplicateException;
import com.epam.spring.core.shared.NotFoundException;
import org.joda.time.LocalDate;

import java.util.List;

public interface UserService {

    /**
     * Register new user to the Booking system.
     *
     * @param name     user name. Cannot be {@code null} or empty. Should be unique.
     * @param email    user e-mail. Cannot be {@code null} or empty. Should be unique.
     * @param birthday user birthday. Cannot be {@code null} or in future.
     * @return registered user.
     * @throws DuplicateException       if user with passed email or name already registered.
     * @throws IllegalArgumentException if any passed parameters are incorrect.
     */
    User register(String name, String email, LocalDate birthday) throws IllegalArgumentException, DuplicateException;

    /**
     * Find user with specific ID.
     *
     * @param id user id for search.
     * @return found user.
     * @throws IllegalArgumentException if passed id is {@code null}.
     * @throws NotFoundException        if user with with specific ID doesn't exist.
     */
    User getById(Long id) throws IllegalArgumentException, NotFoundException;

    /**
     * Find user with specific email.
     *
     * @param email user email for search.
     * @return found user.
     * @throws IllegalArgumentException if passed email is {@code null} or empty.
     * @throws NotFoundException        if user with with specific email doesn't exist.
     */
    User getByEmail(String email) throws IllegalArgumentException, NotFoundException;

    /**
     * Find user with specific name.
     *
     * @param name user name for search.
     * @return found user.
     * @throws IllegalArgumentException if passed name is {@code null} or empty.
     * @throws NotFoundException        if user with with specific name doesn't exist.
     */
    User getByName(String name) throws IllegalArgumentException, NotFoundException;

    /**
     * Find all registered users.
     *
     * @return list of all registered users or empty list.
     */
    List<UserEntity> getAll();

}
