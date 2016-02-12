package com.epam.spring.core.auditoriums;

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

    @Override
    public Auditorium getAuditorium(final String name) {
        Auditorium  res = null;

        for (Auditorium auditorium : auditoriums) {
            if (name.equals(auditorium.getName())) {
                res = auditorium;
            }
        }

        return res;
    }
}
