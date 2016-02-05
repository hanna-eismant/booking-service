package com.epam.spring.core.auditoriums.services;

import com.epam.spring.core.auditoriums.Auditorium;

import java.util.ArrayList;
import java.util.List;

public class AuditoriumServiceImpl implements AuditoriumService {

    private List<Auditorium> auditoriums = new ArrayList<>();

    public AuditoriumServiceImpl(List<Auditorium> auditoriums) {
        this.auditoriums = auditoriums;
    }

    @Override
    public List<Auditorium> getAuditoriums() {
        return auditoriums;
    }

}
