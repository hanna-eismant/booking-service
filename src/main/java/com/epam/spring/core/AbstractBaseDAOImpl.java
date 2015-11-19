package com.epam.spring.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.INTEGER;

public abstract class AbstractBaseDAOImpl<T extends BaseEntity> implements BaseDAO<T> {

    @Autowired
    protected JdbcTemplate jdbcTemplate;

    // first inserted row will have id = 1
    protected static long idCounter = 0;

    // parameters for create statement
    protected Object[] args = new Object[] {};
    protected int[] argTypes = new  int[] {};

    @Override
    public T create(T entity) throws IllegalArgumentException {
        if (entity == null) {
            throw new IllegalArgumentException("Entity cannot be 'null'");
        }

        entity.id = ++idCounter;

        args[0] = entity.id;
        argTypes[0] = INTEGER;

        jdbcTemplate.update(getCreateSql(), args, argTypes);
        return findById(entity.id);
    }

    @Override
    public void remove(T t) {
        jdbcTemplate.update(getRemoveSql(), t.id);
    }

    @Override
    public T findById(Long id) {
        T event = null;

        try {
            event = jdbcTemplate.queryForObject(getFindByIdSql(), new Object[]{id}, createMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no row found then return null
        }

        return event;
    }

    @Override
    public List<T> findAll() {
        List<T> events = new ArrayList<>();

        try {
            events = jdbcTemplate.query(getFindAllSql(), new Object[]{}, createMapper());
        } catch (EmptyResultDataAccessException e) {
            // if no rows found then return empty list
        }

        return events;
    }

    abstract protected String getCreateSql();
    abstract protected String getRemoveSql();
    abstract protected String getFindByIdSql();
    abstract protected String getFindAllSql();

    abstract protected RowMapper<T> createMapper();
}
