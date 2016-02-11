package com.epam.spring.core.users;

import com.epam.spring.core.tickets.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Override
    public Map<String, Object> getUserInfo(final String name) {
        User user = userService.getByName(name);
        int ticketsCount = ticketService.getBookedTicketsCount(user);

        Map<String, Object> result = new HashMap<>(4);

        result.put("name", user.getName());
        result.put("email", user.getEmail());
        result.put("birthday", user.getBirthday());
        result.put("ticketsCount", ticketsCount);

        return result;
    }

    @Override
    public List<User> getAllUsersInfo() {
        return userService.getAll();
    }
}
