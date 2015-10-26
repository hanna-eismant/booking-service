package com.epam.spring.core.tickets;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.users.User;

import java.util.Date;

public class Ticket {

    public User user;
    public Date date;
    public Event event;
    public Integer seat;
    public Integer basePrice;
    public Integer discountPrice;

    public Ticket() {
    }

    public Ticket(Date date, Event event, Integer seat, Integer basePrice) {
        this.date = date;
        this.event = event;
        this.seat = seat;
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "user=" + user +
                ", date=" + date +
                ", seat=" + seat +
                ", basePrice=" + basePrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
