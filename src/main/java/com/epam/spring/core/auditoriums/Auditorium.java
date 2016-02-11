package com.epam.spring.core.auditoriums;

import java.util.ArrayList;
import java.util.List;

public class Auditorium {

    private String name;
    private Integer seats;
    private List<Integer> vipSeats = new ArrayList<>();

    public Auditorium(final String _name, final Integer _seats, final List<Integer> _vipSeats) {
        name = _name;
        seats = _seats;
        vipSeats = _vipSeats;
    }

    public String getName() {
        return name;
    }

    public void setName(final String _name) {
        name = _name;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(final Integer _seats) {
        seats = _seats;
    }

    public List<Integer> getVipSeats() {
        return vipSeats;
    }
}
