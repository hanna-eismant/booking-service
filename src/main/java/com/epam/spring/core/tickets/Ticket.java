package com.epam.spring.core.tickets;

import com.epam.spring.core.users.User;

public class Ticket {

    public Long id;
    public User user;
    public Integer seat;
    public Integer basePrice;
    public Integer discountPrice;

    public Ticket() {
    }

    public Ticket(Integer seat, Integer basePrice) {
        this.seat = seat;
        this.basePrice = basePrice;
    }
}
