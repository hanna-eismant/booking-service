package com.epam.spring.core.users.services;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.users.User;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.epam.spring.core.TestUtils.*;
import static org.junit.Assert.*;

public class UserServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UserService userService;

    private User user;
    private Long userId;

    @Before
    public void setUp() throws Exception {
        user = null;
        userId = null;
    }

    @Test
    public void testRegister() throws Exception {
        user = userService.register(USER_NAME, USER_EMAIL, USER_BIRTHDAY);
        assertNotNull("Registered user cannot be null", user);
        assertNotNull("Registered user should have id", user.id);
        assertEquals(user.name, USER_NAME);
        assertEquals(user.email, USER_EMAIL);
        assertEquals(user.birthday, USER_BIRTHDAY);
    }

    @Test
    public void testGetByIdRegistered() throws Exception {
        user = userService.register(USER_NAME, USER_EMAIL, USER_BIRTHDAY);
        userId = user.id;

        User userTest = userService.getById(userId);
        assertNotNull(userTest);
        assertEquals(userId, userTest.id);
    }

    @Test
    public void testGetByIdUnregistered() {
        User user = userService.getById(1L);
        assertNull(user);
    }
}
