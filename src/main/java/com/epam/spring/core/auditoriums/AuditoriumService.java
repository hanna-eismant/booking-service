package com.epam.spring.core.auditoriums;

import java.util.List;

public interface AuditoriumService {

    List<Auditorium> getAuditoriums();

    Auditorium getAuditorium(final String name);
}
