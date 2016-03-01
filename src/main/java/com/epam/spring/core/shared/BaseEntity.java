package com.epam.spring.core.shared;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(final Long _id) {
        id = _id;
    }
}
