package com.epam.spring.core.tickets.seervices;

import com.epam.spring.core.discounts.DiscountService;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Rating;
import com.epam.spring.core.tickets.dao.TicketDAO;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketDAO ticketDAO;

    @Autowired
    private DiscountService discountService;

    @Override
    public Double getTicketPrice(Event event, LocalDateTime date, Integer seat, boolean isVip, User user) {
        Double discountPercent = 0.0d;
        Double resultPrice = event.basePrice;

        if (Rating.HIGH.equals(event.rating)) {
            resultPrice *= 1.2d;
        }

        if (isVip) {
            resultPrice *= 2.0d;
        }

        if (user != null) {
            discountPercent = discountService.getDiscount(user, event, date);
        }

        return resultPrice * (1 - discountPercent);
    }
}
