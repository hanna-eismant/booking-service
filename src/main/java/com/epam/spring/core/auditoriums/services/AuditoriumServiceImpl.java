package com.epam.spring.core.auditoriums.services;

import com.epam.spring.core.auditoriums.Auditorium;

import java.util.ArrayList;
import java.util.List;

public class AuditoriumServiceImpl implements AuditoriumService {

    private List<Auditorium> auditoriums = new ArrayList<>();

    public AuditoriumServiceImpl(List<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    public void init() {
        System.out.println();
        System.out.println(auditoriums);
    }

    @Override
    public List<Auditorium> getAuditoriums() {
        return auditoriums;
    }

    @Override
    public Integer getSeatsNumber() {
        return null;
    }

    @Override
    public Integer getVipSeats() {
        return null;
    }
}
