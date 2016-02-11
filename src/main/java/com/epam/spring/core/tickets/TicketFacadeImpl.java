package com.epam.spring.core.tickets;

import com.epam.spring.core.users.User;
import com.epam.spring.core.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TicketFacadeImpl implements TicketFacade {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Override
    public Map<String, Object> getUserTickets(final String userName) {
        Map<String, Object> result = new HashMap<>();

        User user = userService.getByName(userName);
        List<Ticket> tickets = ticketService.getBookedTickets(user);

        result.put("user", user);
        result.put("tickets", tickets);

        return result;
    }
}
