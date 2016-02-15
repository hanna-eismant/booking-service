package com.epam.spring.core.events;

import com.epam.spring.core.shared.BaseDAO;

import java.util.List;

public interface ShowDAO extends BaseDAO<Show> {

    List<Show> getByEvent(final Long eventId);
}
