package com.epam.spring.core.auditoriums;

import java.util.ArrayList;
import java.util.List;

public class Auditorium {

    public String name;
    public Integer seats;
    private List<Integer> vipSeats = new ArrayList<Integer>();

    public Auditorium() {
    }

    public Auditorium(String name, Integer seats, List<Integer> vipSeats) {
        this.name = name;
        this.seats = seats;
        this.vipSeats = vipSeats;
    }

    public List<Integer> getVipSeats() {
        return vipSeats;
    }
}
