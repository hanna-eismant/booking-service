package com.epam.spring.core.users;

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
     * @throws Exception                if user with passed email already registered.
     * @throws IllegalArgumentException if any passed parameters are incorrect.
     */
    User register(String name, String email, LocalDate birthday) throws Exception;

    /**
     * Remove user from system.
     *
     * @param user user for delete.
     */
    void remove(User user);

    /**
     * Find user with specific ID.
     *
     * @param id user id for search.
     * @return found user or {@code null}.
     */
    User getById(Long id);

    /**
     * Find user with specific email.
     *
     * @param email user email for search.
     * @return found user or {@code null}.
     */
    User getByEmail(String email);

    /**
     * Find users with specific name.
     *
     * @param name user name for search.
     * @return found user or {@code null}.
     */
    User getByName(String name);

    /**
     * Find all registered users.
     *
     * @return list of all registered users or empty list.
     */
    List<UserEntity> getAll();

}
