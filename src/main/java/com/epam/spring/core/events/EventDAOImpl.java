package com.epam.spring.core.events;

import com.epam.spring.core.shared.AbstractBaseDAOImpl;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Types.BIGINT;
import static java.sql.Types.DOUBLE;
import static java.sql.Types.VARCHAR;

@Repository("eventDAO")
public class EventDAOImpl extends AbstractBaseDAOImpl<Event> implements EventDAO {

    private long idCounter = 10;

    private static final String COLUMN_LIST = "";

    private static final String CREATE_SQL = "INSERT INTO events (id,name,base_price,rating) VALUES (?,?,?,?)";
    private static final String REMOVE_SQL = "DELETE FROM events WHERE id = ?";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM events WHERE id = ?";
    private static final String FIND_BY_SHOW_SQL = "SELECT e.name, e.rating, e.id, e.base_price FROM events e LEFT JOIN shows s ON s.event_id = e.id WHERE s.id = ?";
    private static final String FIND_ALL_SQL = "SELECT * FROM events ORDER BY name ASC";

    @Override
    public Event findByShow(final Long showId) {
        Event entity = null;

        try {
            entity = jdbcTemplate.queryForObject(FIND_BY_SHOW_SQL, new Object[]{showId}, createMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no rows found then return empty list
        }

        return entity;
    }

    @Override
    protected long generateId() {
        return ++idCounter;
    }

    @Override
    protected int[] getArgTypes() {
        return new int[] {BIGINT, VARCHAR, DOUBLE, VARCHAR};
    }

    @Override
    protected Object[] getArgsForCreate(final Event entity) {
        return new Object[]{entity.getId(), entity.getName(), entity.getBasePrice(), entity.getRating().toString()};
    }

    @Override
    protected String getFindByIdSql() {
        return FIND_BY_ID_SQL;
    }

    @Override
    protected String getCreateSql() {
        return CREATE_SQL;
    }

    @Override
    protected String getRemoveSql() {
        return REMOVE_SQL;
    }

    @Override
    protected String getFindAllSql() {
        return FIND_ALL_SQL;
    }

    @Override
    protected RowMapper<Event> createMapper() {
        return new EventRowMapper();
    }

    class EventRowMapper implements RowMapper<Event> {

        @Override
        public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
            Event event = new Event();
            event.setId(rs.getLong("id"));
            event.setName(rs.getString("name"));
            event.setBasePrice(rs.getDouble("base_price"));
            event.setRating(Rating.valueOf(rs.getString("rating")));

            return event;
        }
    }
}
