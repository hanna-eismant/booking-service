package com.epam.spring.core.shared;

public abstract class BaseEntity {
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(final Long _id) {
        id = _id;
    }
}
