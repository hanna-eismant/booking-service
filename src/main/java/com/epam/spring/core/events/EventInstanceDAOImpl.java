package com.epam.spring.core.events;

import com.epam.spring.core.auditoriums.AuditoriumService;
import com.epam.spring.core.shared.AbstractBaseDAOImpl;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.BIGINT;
import static java.sql.Types.DOUBLE;
import static java.sql.Types.VARCHAR;

@Repository("eventInstanceDAO")
public class EventInstanceDAOImpl extends AbstractBaseDAOImpl<EventInstance> implements EventInstanceDAO {

    private long idCounter = 0;

    private static final String CREATE_SQL = "INSERT INTO event_instances (id,date,auditorium,event_id) VALUES (?,?,?,?)";
    private static final String REMOVE_SQL = "DELETE FROM event_instances WHERE id = ?";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM event_instances WHERE id = ?";
    private static final String FIND_ALL_SQL = "SELECT * FROM event_instances";

    private static final String FIND_BY_EVENT_SQL = "SELECT * FROM event_instances WHERE event_id = ?";

    @Autowired
    private AuditoriumService auditoriumService;


    @Override
    public List<EventInstance> getByEvent(final Long eventId) {
        if (eventId == null) {
            throw new IllegalArgumentException("Event id for search cannot be 'null'");
        }

        List<EventInstance> list = new ArrayList<>();
        try {
            list = jdbcTemplate.query(FIND_BY_EVENT_SQL, new Object[]{eventId}, createMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no entities found, then return empty list
        }

        return list;
    }

    @Override
    protected long generateId() {
        return ++idCounter;
    }

    @Override
    protected int[] getArgTypes() {
        return new int[]{BIGINT, VARCHAR, DOUBLE, VARCHAR};
    }

    @Override
    protected Object[] getArgsForCreate(final EventInstance entity) {
        return new Object[]{entity.getId(), entity.getDate(), entity.getAuditorium().getName(), entity.getEvent().getId()};
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
    protected RowMapper<EventInstance> createMapper() {
        return (rs, rowNum) -> {
            EventInstance instance = new EventInstance();
            instance.setId(rs.getLong("id"));
            instance.setDate(LocalDateTime.parse(rs.getString("date")));
            instance.setAuditorium(auditoriumService.getAuditorium(rs.getString("auditorium")));
            return instance;
        };
    }
}
