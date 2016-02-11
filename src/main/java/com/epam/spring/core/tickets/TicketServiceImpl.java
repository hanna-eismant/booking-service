package com.epam.spring.core.tickets;

import com.epam.spring.core.discounts.DiscountService;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Rating;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("ticketService")
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private DiscountService discountService;

    @Override
    public Double getTicketPrice(Event event, LocalDateTime date, Integer seat, boolean isVip, User user) {
        Double discountPercent = 0.0d;
        Double resultPrice = event.getBasePrice();

        if (Rating.HIGH.equals(event.getRating())) {
            resultPrice *= 1.2d; // todo: magic number!
        }

        if (isVip) {
            resultPrice *= 2.0d; // todo: magic number!
        }

        if (user != null) {
            discountPercent = discountService.getDiscount(user, event, date);
        }

        return resultPrice * (1 - discountPercent);
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        return ticketDAO.findByUser(user);
    }

    @Override
    public int getBookedTicketsCount(User user) {
        // todo: create special request to get count
        return getBookedTickets(user).size();
    }
}
