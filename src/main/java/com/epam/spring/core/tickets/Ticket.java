package com.epam.spring.core.tickets;

import com.epam.spring.core.BaseEntity;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDateTime;

public class Ticket extends BaseEntity {

    public User user;
    public LocalDateTime date;
    public Event event;
    public Integer seat;
    public boolean isVip;
    public Double basePrice;
    public Double discountPrice;

    public Ticket() {
    }

    public Ticket(LocalDateTime date, Event event, Integer seat, boolean isVip, Double basePrice) {
        this.date = date;
        this.event = event;
        this.seat = seat;
        this.isVip = isVip;
        this.basePrice = basePrice;

        if (this.isVip) {
            this.basePrice *= 2;
        }
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", event='" + (event == null ? "" : event.name) +
                "', user=" + user +
                ", date=" + date +
                ", seat=" + seat +
                ", isVip=" + isVip +
                ", basePrice=" + basePrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
