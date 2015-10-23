package com.epam.spring.core.events;

import com.epam.spring.core.tickets.Ticket;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Scope("prototype")
public class Event {

    public Long id;
    public String name;
    public Integer basePrice;
    public Rating rating;
    private List<Ticket> tickets = new ArrayList<Ticket>();

    public Event() {
        // todo: set id
    }

    public Event(String name, Integer basePrice, Rating rating) {
        // todo: set id
        this.name = name;
        this.basePrice = basePrice;
        this.rating = rating;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", basePrice=" + basePrice +
                ", rating=" + rating +
                ", tickets(" + tickets.size() + ")=" + tickets +
                '}';
    }
}
