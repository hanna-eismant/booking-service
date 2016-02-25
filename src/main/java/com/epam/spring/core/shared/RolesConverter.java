package com.epam.spring.core.shared;

import com.epam.spring.core.users.UserRoles;
import com.google.common.collect.Iterables;
import org.hsqldb.jdbc.JDBCArray;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Converter(autoApply = true)
public class RolesConverter implements AttributeConverter<List<UserRoles>, Object> {

    @Override
    public Object convertToDatabaseColumn(final List<UserRoles> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }
        return Iterables.toArray(attribute, UserRoles.class);
    }

    @Override
    public List<UserRoles> convertToEntityAttribute(final Object dbData) {
        if (!(dbData instanceof JDBCArray)) {
            return Collections.emptyList();
        }

        JDBCArray jdbcArray = (JDBCArray) dbData;
        Object[] internal = jdbcArray.getArrayInternal();
        List<UserRoles> result = new ArrayList<>(internal.length);

        for (Object anInternal : internal) {
            result.add(UserRoles.valueOf(anInternal.toString()));
        }

        return result;
    }
}
