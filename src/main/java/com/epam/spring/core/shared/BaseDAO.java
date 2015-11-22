package com.epam.spring.core.shared;

import java.util.List;

public interface BaseDAO<T> {

    /**
     * Create new entity in database.
     *
     * @param entity entity object with filled fields exclude {@link BaseEntity#id}.
     * @return saved entity with ID.
     * @throws IllegalArgumentException if passed entity is {@code null}.
     */
    T create(T entity) throws IllegalArgumentException;

    void remove(T entity) throws IllegalArgumentException;

    T findById(Long id) throws IllegalArgumentException;

    List<T> findAll();

}
