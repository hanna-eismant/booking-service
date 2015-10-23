package com.epam.spring.core.events;

import com.epam.spring.core.tickets.Ticket;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
@Scope("prototype")
public class Event {

    public Long id;
    public String name;
    private List<Date> airDates = new ArrayList<Date>();
    private List<Date> airTimes = new ArrayList<Date>();
    public Integer basePrice;
    public Rating rating;
    private List<Ticket> tickets = new ArrayList<Ticket>();

    public Event() {
        // todo: set id
    }

    public Event(String name, List<Date> airDates, List<Date> airTimes, Integer basePrice, Rating rating) {
        // todo: set id
        this.name = name;
        this.airDates = airDates;
        this.airTimes = airTimes;
        this.basePrice = basePrice;
        this.rating = rating;
    }

    public List<Date> getAirDates() {
        return airDates;
    }

    public List<Date> getAirTimes() {
        return airTimes;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", airDates=" + airDates +
                ", airTimes=" + airTimes +
                ", basePrice=" + basePrice +
                ", rating=" + rating +
                ", tickets=" + tickets +
                '}';
    }
}
