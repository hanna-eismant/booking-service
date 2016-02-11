package com.epam.spring.core.events;

import com.epam.spring.core.shared.AbstractBaseDAOImpl;
import org.springframework.jdbc.core.RowMapper;

import static java.sql.Types.BIGINT;
import static java.sql.Types.DOUBLE;
import static java.sql.Types.VARCHAR;

public class EventInstanceDAOImpl extends AbstractBaseDAOImpl<EventInstance> implements EventInstanceDAO {

    private long idCounter = 0;

    private static final String CREATE_SQL = "INSERT INTO event_instances (id,date,auditorium,event_id) VALUES (?,?,?,?)";
    private static final String REMOVE_SQL = "DELETE FROM event_instances WHERE id = ?";
    private static final String FIND_BY_ID_SQL = "SELECT * FROM event_instances WHERE id = ?";
    private static final String FIND_ALL_SQL = "SELECT * FROM event_instances";

    @Override
    protected long generateId() {
        return ++idCounter;
    }

    @Override
    protected int[] getArgTypes() {
        return new int[] {BIGINT, VARCHAR, DOUBLE, VARCHAR};
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
        return null;
    }
}
