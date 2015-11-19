package com.epam.spring.core.events.dao;

import com.epam.spring.core.AbstractBaseDAOImpl;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.Rating;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import static java.sql.Types.*;

@Repository("eventDAO")
public class EventDAOImpl extends AbstractBaseDAOImpl<Event> implements EventDAO {

    private static final String CREATE_SQL = "INSERT INTO events (id,name,base_price,rating) VALUES (?,?,?,?)";
    private static final String REMOVE_SQL = "DELETE FROM events WHERE id = ?";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM events WHERE id = ?";
    private static final String FIND_ALL_SQL = "SELECT * FROM events";

    @Override
    public Event create(Event entity) throws IllegalArgumentException {
        args = new Object[]{entity.id, entity.name, entity.basePrice, entity.rating.toString()};
        argTypes = new int[] {INTEGER, VARCHAR, DOUBLE, VARCHAR};

        return super.create(entity);
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
            event.id = rs.getLong("id");
            event.name = rs.getString("name");
            event.basePrice = rs.getDouble("base_price");
            event.rating = Rating.valueOf(rs.getString("rating"));

            return event;
        }
    }
}
