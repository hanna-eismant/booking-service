package com.epam.spring.core.auditoriums.services;

import com.epam.spring.core.auditoriums.Auditorium;

import java.util.ArrayList;
import java.util.List;

public class AuditoriumServiceImpl implements AuditoriumService {

    private List<Auditorium> auditoriums = new ArrayList<Auditorium>();

    public static List<Integer> parseVip(String auditoriumVips) {
        List<Integer> result = new ArrayList<Integer>();
        String[] split = auditoriumVips.split(",");

        for (String s : split) {
            result.add(Integer.parseInt(s));
        }

        return result;
    }

    public AuditoriumServiceImpl(List<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    public List<Auditorium> getAuditoriums() {
        return auditoriums;
    }

    public Integer getSeatsNumber() {
        return null;
    }

    public Integer getVipSeats() {
        return null;
    }
}
