package com.epam.spring.core.booking;

import com.epam.spring.core.shared.exceptions.NotEnoughMoneyException;
import com.epam.spring.core.shared.exceptions.NotFoundException;
import com.epam.spring.core.shared.exceptions.TicketAlreadyBookedException;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.tickets.TicketService;
import com.epam.spring.core.users.User;
import com.epam.spring.core.users.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("bookingService")
public class BookingServiceImpl implements BookingService {

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public Ticket bookTicket(String userName, Long ticketId) throws NotFoundException, TicketAlreadyBookedException, NotEnoughMoneyException {
        // todo: check parameters

        Ticket ticket = ticketService.getById(ticketId);
        User user = userService.getByName(userName);

        if (ticket.getUser() != null && user.equals(ticket.getUser())) {
            // same user → do nothing
            return ticket;
        } else if (ticket.getUser() != null && !user.equals(ticket.getUser())) {
            // another user → exception
            throw new TicketAlreadyBookedException("Ticket is already booked by another user");
        }

        Double ticketPrice = ticketService.getTicketPrice(ticket, user);


        Double money = user.getAccount().getMoney();
        money -= ticketPrice;

        if (money < 0) {
            throw new NotEnoughMoneyException("User has not enough money to book ticket");
        }

        ticket.setDiscountPrice(ticketPrice);
        ticket.setUser(user);
        user.getAccount().setMoney(money);

        userService.withdraw(user, ticketPrice);
        ticket = ticketService.update(ticket);

        return ticket;
    }
}
