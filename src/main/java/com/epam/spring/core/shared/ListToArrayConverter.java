package com.epam.spring.core.shared;

import com.google.common.collect.Iterables;
import org.hsqldb.jdbc.JDBCArray;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Converter(autoApply = true)
    public class ListToArrayConverter implements AttributeConverter<List<Integer>, Object> {

    @Override
    public Object convertToDatabaseColumn(final List<Integer> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        return Iterables.toArray(attribute, Integer.class);
    }

    @Override
    public List<Integer> convertToEntityAttribute(final Object dbData) {
        if (!(dbData instanceof JDBCArray)) {
            return Collections.emptyList();
        }

        JDBCArray jdbcArray = (JDBCArray) dbData;
        Object[] internal = jdbcArray.getArrayInternal();
        List<Integer> result = new ArrayList<>(internal.length);

        for (Object anInternal : internal) {
            result.add((Integer) anInternal);
        }

        return result;
    }
}
