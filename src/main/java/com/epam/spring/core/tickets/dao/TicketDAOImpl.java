package com.epam.spring.core.tickets.dao;

import com.epam.spring.core.AbstractBaseDAOImpl;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.users.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TicketDAOImpl extends AbstractBaseDAOImpl<Ticket> implements TicketDAO {


    @Override
    public Ticket create(Ticket entity) throws IllegalArgumentException {


        return super.create(entity);
    }

    @Override
    public Ticket update(Ticket ticket) {
        return null;
    }

    @Override
    public List<Ticket> findByEvent(Event event) {
        return null;
    }

    @Override
    public List<User> findByUser(User user) {
        return null;
    }

    @Override
    protected String getCreateSql() {
        return null;
    }

    @Override
    protected String getRemoveSql() {
        return null;
    }

    @Override
    protected String getFindByIdSql() {
        return null;
    }

    @Override
    protected String getFindAllSql() {
        return null;
    }

    @Override
    protected RowMapper<Ticket> createMapper() {
        return null;
    }


}
