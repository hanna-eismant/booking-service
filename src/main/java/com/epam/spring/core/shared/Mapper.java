package com.epam.spring.core.shared;

import com.epam.spring.core.api.soap.SoapUser;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.converter.builtin.PassThroughConverter;
import ma.glasnost.orika.impl.DefaultMapperFactory;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import com.epam.spring.core.auditoriums.Auditorium;
import com.epam.spring.core.auditoriums.AuditoriumEntity;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.events.EventEntity;
import com.epam.spring.core.events.Show;
import com.epam.spring.core.events.ShowEntity;
import com.epam.spring.core.users.User;
import com.epam.spring.core.users.UserAccount;
import com.epam.spring.core.users.UserAccountEntity;
import com.epam.spring.core.users.UserEntity;

public class Mapper {

    private static MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    static {
        mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDate.class));
        mapperFactory.getConverterFactory().registerConverter(new PassThroughConverter(LocalDateTime.class));

        mapperFactory.classMap(UserEntity.class, User.class).byDefault().register();
        mapperFactory.classMap(User.class, SoapUser.class).byDefault().register();

        mapperFactory.classMap(UserAccountEntity.class, UserAccount.class).byDefault().register();

        mapperFactory.classMap(EventEntity.class, Event.class).byDefault().register();

        mapperFactory.classMap(ShowEntity.class, Show.class).byDefault().register();

        mapperFactory.classMap(AuditoriumEntity.class, Auditorium.class).byDefault().register();
    }

    public static MapperFacade getMapper() {
        return mapperFactory.getMapperFacade();
    }
}
