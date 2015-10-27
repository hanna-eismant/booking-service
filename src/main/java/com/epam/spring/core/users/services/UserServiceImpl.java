package com.epam.spring.core.users.services;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.services.EventService;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;
import com.epam.spring.core.users.dao.UserDAO;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private EventService eventService;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Override
    public User register(String name, String email, LocalDate birthday) throws Exception {
        // check user name
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("User name cannot be empty or 'null'");
        }

        // check user email
        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("User email cannot be empty or 'null'");
        }
        User byEmail = userDAO.findByEmail(email);
        if (byEmail != null) {
            throw new Exception("User email '" + email + "' is duplicated");
        }

        // check user birthday
        LocalDate currentDate = LocalDate.now();
        if (birthday == null) {
            throw new IllegalArgumentException("User birthday cannot be 'null'");
        }
        if (birthday.isAfter(currentDate) || birthday.isEqual(currentDate)) {
            throw new IllegalArgumentException("User birthday cannot be in future or today");
        }

        User user = new User(name, email, birthday);
        user = userDAO.create(user);

        System.out.println("Register new user: " + user);

        return user;
    }

    @Override
    public void remove(User user) {
        userDAO.remove(user);
    }

    @Override
    public User getById(Long id) {
        return userDAO.findById(id);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    @Override
    public List<User> getUsersByName(String name) {
        return userDAO.findByName(name);
    }

    @Override
    public List<User> getAll() {
        return userDAO.findAll();
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        List<Ticket> result = new ArrayList<>();

        List<Event> allEvents = eventService.getAll();

        for (Event event : allEvents) {
            List<Ticket> tickets = event.getTickets();
            for (Ticket ticket : tickets) {
                if (user.equals(ticket.user)) {
                    result.add(ticket);
                }
            }
        }

        return result;
    }

    @Override
    public int getBookedTicketsCount(User user) {
        int result = 0;

        List<Event> allEvents = eventService.getAll();

        for (Event event : allEvents) {
            List<Ticket> tickets = event.getTickets();
            for (Ticket ticket : tickets) {
                if (user.equals(ticket.user)) {
                    result++;
                }
            }
        }

        return result;
    }
}
