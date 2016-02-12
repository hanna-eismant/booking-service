package com.epam.spring.core.events;

import com.epam.spring.core.shared.BaseDAO;

import java.util.List;

public interface EventInstanceDAO extends BaseDAO<EventInstance> {

    List<EventInstance> getByEvent(final Long eventId);
}
