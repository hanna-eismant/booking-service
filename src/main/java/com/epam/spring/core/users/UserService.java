package com.epam.spring.core.users;

import com.epam.spring.core.tickets.Ticket;
import org.joda.time.LocalDate;

import java.util.List;

public interface UserService {

    /**
     * Register new user to the Booking system.
     *
     * @param name     user name. Cannot be {@code null} or empty.
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
    User getUserByEmail(String email);

    /**
     * Find users with specific name.
     *
     * @param name user name for search.
     * @return found users list ir empty list.
     */
    List<User> getUsersByName(String name);

    /**
     * Find all booked tickets by user.
     *
     * @param user user for search.
     * @return list of all booked tickets by user. If user has no booked tickets then return empty list.
     */
    List<Ticket> getBookedTickets(User user);

    /**
     * Find all registered users.
     *
     * @return list of all registered users or empty list.
     */
    List<User> getAll();

    /**
     * Calculate how many tickets has booked specified user.
     */
    int getBookedTicketsCount(User user);
}
