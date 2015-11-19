package com.epam.spring.core.events.dao;

import com.epam.spring.core.Util;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Rating;
import com.epam.spring.core.users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("eventDAO")
public class EventDAOImpl implements EventDAO {

    private static final String CREATE_SQL = "INSERT INTO events (id,name,base_price,rating) VALUES (?,?,?,?)";
    private static final String REMOVE_SQL = "DELETE FROM events WHERE id = ?";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM events WHERE id = ?";
    private static final String FIND_ALL_SQL = "SELECT * FROM events";
    private static final String REMOVE_ALL_SQL = "DELETE FROM events";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Event create(Event event) {

    }

    @Override
    public void remove(Event event) {

    }

    @Override
    public Event findById(Long id) {

    }

    @Override
    public List<Event> findAll() {

    }

    @Override
    public void removeAll() {

    }

    class EventRowMapper implements RowMapper<Event> {

        @Override
        public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
            Event event = new Event();
            event.id = rs.getLong("id");
            event.name = rs.getString("name");
            event.basePrice = rs.getDouble("base_price");
            event.rating = Rating.valueOf(rs.getString("rating"));

            return event;
        }
    }
}
