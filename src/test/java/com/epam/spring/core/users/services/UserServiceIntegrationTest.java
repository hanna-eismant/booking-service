package com.epam.spring.core.users.services;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.users.User;
import com.epam.spring.core.users.dao.UserDAO;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserServiceIntegrationTest extends AbstractIntegrationTest {

    public static final String HANNA_BIRTHDAY = "1990-12-15";
    public static final String ADMIN_BIRTHDAY = "1987-04-04";

    @Autowired
    private UserService userService;

    @Autowired
    private UserDAO userDAO;

    private User hanna;
    private Long hannaId;
    private String hannaName;
    private String hannaEmail;
    private LocalDate hannaBirthDay;

    private User admin;
    private Long adminId;
    private String adminName;
    private String adminEmail;
    private LocalDate adminBirthDay;

    @Before
    public void setUp() throws Exception {
        hanna = null;
        hannaId = null;
        hannaName = "Hanna";
        hannaEmail = "Hanna@Mail";
        hannaBirthDay = LocalDate.parse(HANNA_BIRTHDAY);

        admin = null;
        adminId = null;
        adminName = "Admin";
        adminEmail = "Admin@Mail";
        adminBirthDay = LocalDate.parse(ADMIN_BIRTHDAY);

        userDAO.removeAll();
    }

    @Test
    public void testRegister() throws Exception {
        hanna = userService.register(hannaName, hannaEmail, hannaBirthDay);
        assertNotNull("Registered user cannot be null", hanna);
        assertNotNull("Registered user should have id", hanna.id);
        assertEquals(hanna.name, hannaName);
        assertEquals(hanna.email, hannaEmail);
        assertEquals(hanna.birthday, hannaBirthDay);
        hannaId = hanna.id;
    }

    @Test
    public void testGetByIdRegistered() throws Exception {
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
