package com.epam.spring.core;

import java.util.List;

public interface BaseDAO<T> {

    T create(T entity) throws IllegalArgumentException;

    void remove(T entity) throws IllegalArgumentException;

    T findById(Long id) throws IllegalArgumentException;

    List<T> findAll();

}
