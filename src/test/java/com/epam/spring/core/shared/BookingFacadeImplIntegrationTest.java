package com.epam.spring.core.shared;

import com.epam.spring.core.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class BookingFacadeImplIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private BookingFacade facade;

    @Test
    public void testGetAllEventsInfo() {
        facade.getAllEventsInfo();
    }

    @Test
    public void testGetEventInfo() throws NotFoundException {
        facade.getEventInfo(1L);
    }

    @Test
    public void testGetUserInfo() throws NotFoundException {
        facade.getUserInfo("jane");
    }

    @Test
    public void testGetUser() throws NotFoundException {
        facade.getUser("admin");
    }

    @Test
    public void testGetAllUsersInfo() {
        facade.getAllUsersInfo();
    }
}
