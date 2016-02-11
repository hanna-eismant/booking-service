package com.epam.spring.core.shared;

public class Statistic extends BaseEntity {
    private String name;
    private String type;
    private Long counter;

    public String getName() {
        return name;
    }

    public void setName(final String _name) {
        name = _name;
    }

    public String getType() {
        return type;
    }

    public void setType(final String _type) {
        type = _type;
    }

    public Long getCounter() {
        return counter;
    }

    public void setCounter(final Long _counter) {
        counter = _counter;
    }
}
