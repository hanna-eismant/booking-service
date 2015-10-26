package com.epam.spring.core.users.services;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.*;

public class UserServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UserService userService;

    private User hanna;
    private Long hannaId;
    private String hannaName;
    private String hannaEmail;
    private Date hannaBirthDay;

    private User admin;
    private Long adminId;
    private String adminName;
    private String adminEmail;
    private Date adminBirthDay;

    @Before
    public void setUp() throws Exception {
        hanna = null;
        hannaId = null;
        hannaName = "Hanna";
        hannaEmail = "Hanna@Mail";
        hannaBirthDay = LocalDateTime.parse("1990-12-15").toDate();

        admin = null;
        adminId = null;
        adminName = "Admin";
        adminEmail = "Admin@Mail";
        adminBirthDay = LocalDateTime.parse("1987-04-04").toDate();
    }

    @Test
    public void testRegister() {
        hanna = userService.register(hannaName, hannaEmail, hannaBirthDay);
        assertNotNull("Registered user cannot be null", hanna);
        assertNotNull("Registered user should have id", hanna.id);
        assertEquals(hanna.name, hannaName);
        assertEquals(hanna.email, hannaEmail);
        assertEquals(hanna.birthday, hannaBirthDay);
        hannaId = hanna.id;
    }

    @Test
    public void testGetByIdRegistered() {
        hanna = userService.register(hannaName, hannaEmail, hannaBirthDay);
        hannaId = hanna.id;

        User user = userService.getById(hannaId);
        assertNotNull(user);
        assertEquals(hannaId, hanna.id);
    }

    @Test
    public void testGetByIdUnregistered() {
        User user = userService.getById(1L);
        assertNull(user);
    }
}
