package com.epam.spring.core.shared.converters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.hsqldb.jdbc.JDBCArray;

import com.epam.spring.core.users.UserRoles;

@Converter(autoApply = true)
public class RolesConverter implements AttributeConverter<List<UserRoles>, Object> {

    @Override
    public Object convertToDatabaseColumn(final List<UserRoles> attribute) {
        if (attribute == null || attribute.isEmpty()) {
            return null;
        }

        String[] result = new String[attribute.size()];

        for (int i = 0; i < attribute.size(); i++) {
            UserRoles role = attribute.get(i);
            result[i] = role.toString();
        }

        return result;
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
