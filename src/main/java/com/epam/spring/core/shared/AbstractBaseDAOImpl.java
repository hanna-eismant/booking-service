package com.epam.spring.core.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.BIGINT;

public abstract class AbstractBaseDAOImpl<T extends BaseEntity> implements BaseDAO<T> {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    @Override
    public T create(T entity) throws IllegalArgumentException {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be 'null'");
        }

        entity.id = generateId();

        Object[] args = getArgs(entity);
        int[] argTypes = getArgTypes();

        args[0] = entity.id;
        argTypes[0] = BIGINT;

        jdbcTemplate.update(getCreateSql(), args, argTypes);
        return findById(entity.id);
    }

    @Override
    public void remove(T entity) throws IllegalArgumentException {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be 'null'");
        }

        if (entity.id == null) {
            throw new IllegalArgumentException("Entity id cannot be 'null'");
        }

        jdbcTemplate.update(getRemoveSql(), entity.id);
    }

    @Override
    public T findById(Long id) throws IllegalArgumentException {
        if (id == null) {
            throw new IllegalArgumentException("Id for search cannot be 'null'");
        }

        T entity = null;

        try {
            entity = jdbcTemplate.queryForObject(getFindByIdSql(), new Object[]{id}, createMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no row found then return null
        }

        return entity;
    }

    @Override
    public List<T> findAll() {
        List<T> entities = new ArrayList<>();

        try {
            entities = jdbcTemplate.query(getFindAllSql(), new Object[]{}, createMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no rows found then return empty list
        }

        return entities;
    }

    abstract protected long generateId();
    abstract protected int[] getArgTypes();
    abstract protected Object[] getArgs(final T entity);

    abstract protected String getCreateSql();
    abstract protected String getRemoveSql();
    abstract protected String getFindByIdSql();
    abstract protected String getFindAllSql();

    abstract protected RowMapper<T> createMapper();
}
