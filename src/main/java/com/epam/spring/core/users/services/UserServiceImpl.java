package com.epam.spring.core.users.services;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.services.EventService;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;
import com.epam.spring.core.users.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
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
    public User register(String name, String email, Date birthday) {
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
}
