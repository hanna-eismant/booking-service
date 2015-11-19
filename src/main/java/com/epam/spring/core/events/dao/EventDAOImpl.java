package com.epam.spring.core.events.dao;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
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

    private static long eventIdCounter = 0;

    @Override
    public Event create(Event event) {

        event.id = ++eventIdCounter;

        jdbcTemplate.update(CREATE_SQL, event.id, event.name, event.basePrice, event.rating.toString());
        return findById(event.id);
    }

    @Override
    public void remove(Event event) {
        jdbcTemplate.update(REMOVE_SQL, event.id);
    }

    @Override
    public Event findById(Long id) {
        Event event = null;

        try {
            event = jdbcTemplate.queryForObject(FIND_BY_ID_SQL, new Object[]{id}, new EventRowMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no event found then return null
        }

        return event;
    }

    @Override
    public List<Event> findAll() {
        List<Event> events = new ArrayList<>();

        try {
            events = jdbcTemplate.query(FIND_ALL_SQL, new Object[]{}, new EventRowMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no events found then return empty list
        }

        return events;
    }

    @Override
    public void removeAll() {
        jdbcTemplate.update(REMOVE_ALL_SQL);
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
