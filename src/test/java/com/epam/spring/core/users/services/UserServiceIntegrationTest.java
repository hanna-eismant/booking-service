package com.epam.spring.core.users.services;

import com.epam.spring.core.users.User;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class UserServiceIntegrationTest {

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

        admin = userService.register(adminName, adminEmail, adminBirthDay);
        assertNotNull("Registered user cannot be null", admin);
        assertNotNull("Registered user should have id", admin.id);
        assertEquals(admin.name, adminName);
        assertEquals(admin.email, adminEmail);
        assertEquals(admin.birthday, adminBirthDay);
        adminId = admin.id;
    }

    @Test
    public void testRemove() {

    }

    @Test
    public void testGetById() {

    }

    @Test
    public void testGetUserByEmail() {

    }

    @Test
    public void testGetUsersByName() {

    }

    @Test
    public void testGetBookedTickets() {

    }
}
