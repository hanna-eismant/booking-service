package com.epam.spring.core.users.services;

import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;
import com.epam.spring.core.users.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public User register(String name, String email, Date birthday) {
        User user = new User(name, email, birthday);
        user = userDAO.create(user);

        System.out.println("Register new user: " + user);

        return user;
    }

    public void remove(User user) {
        userDAO.remove(user);
    }

    public User getById(Long id) {
        return userDAO.findById(id);
    }

    public User getUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    public List<User> getUsersByName(String name) {
        return userDAO.findByName(name);
    }

    public List<Ticket> getBookedTickets(User user) {
        // TODO: hmmm... how to implement this?
        return null;
    }
}
