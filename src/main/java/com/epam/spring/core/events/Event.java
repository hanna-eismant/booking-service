package com.epam.spring.core.events;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event {

    public Long id;
    public String name;
    private List<Date> airDates = new ArrayList<Date>();
    private List<Date> airTimes = new ArrayList<Date>();
    public Integer basePrice;
    public Rating rating;

    public Event() {
    }

    public Event(String name, List<Date> airDates, List<Date> airTimes, Integer basePrice, Rating rating) {
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
}
