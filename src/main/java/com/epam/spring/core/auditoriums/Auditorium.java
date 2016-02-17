package com.epam.spring.core.auditoriums;

import com.google.common.base.Objects;

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

    public Auditorium() {

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

    @Override
    public boolean equals(final Object _o) {
        if (this == _o) return true;
        if (_o == null || getClass() != _o.getClass()) return false;
        Auditorium that = (Auditorium) _o;
        return Objects.equal(getName(), that.getName()) &&
                Objects.equal(getSeats(), that.getSeats()) &&
                Objects.equal(getVipSeats(), that.getVipSeats());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getName(), getSeats(), getVipSeats());
    }
}
