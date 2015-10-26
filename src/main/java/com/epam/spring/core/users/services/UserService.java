package com.epam.spring.core.users.services;

import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;

import java.util.Date;
import java.util.List;

public interface UserService {

    /**
     *
     * @param name
     * @param email
     * @param birthday
     * @return
     */
    User register(String name, String email, Date birthday);

    void remove(User user);

    User getById(Long id);

    User getUserByEmail(String email);

    List<User> getUsersByName(String name);

    List<Ticket> getBookedTickets(User user);
}
