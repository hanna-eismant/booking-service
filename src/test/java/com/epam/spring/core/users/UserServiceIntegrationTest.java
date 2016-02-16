package com.epam.spring.core.users;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.shared.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.epam.spring.core.TestConstants.USER_BIRTHDAY;
import static com.epam.spring.core.TestConstants.USER_EMAIL;
import static com.epam.spring.core.TestConstants.USER_JANE;
import static com.epam.spring.core.TestConstants.USER_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UserService userService;

    private User user;

    @Before
    public void setUp() throws Exception {
        user = null;
    }

    @Test
    public void testRegister() {
        user = userService.register(USER_NAME, USER_EMAIL, USER_BIRTHDAY);
        assertNotNull("Registered user cannot be null", user);
        assertNotNull("Registered user should have id", user.getId());
        assertEquals(user.getName(), USER_NAME);
        assertEquals(user.getEmail(), USER_EMAIL);
        assertEquals(user.getBirthday(), USER_BIRTHDAY);
    }

    @Test
    public void testGetByIdRegistered() {
        User userTest = userService.getById(USER_JANE.getId());

        assertNotNull(userTest);
        assertEquals(USER_JANE.getId(), userTest.getId());
        assertEquals(USER_JANE.getName(), userTest.getName());
        assertEquals(USER_JANE.getEmail(), userTest.getEmail());
        assertEquals(USER_JANE.getBirthday(), userTest.getBirthday());
    }

    @Test(expected = NotFoundException.class)
    public void testGetByIdUnregistered() {
        userService.getById(1L);
    }
}
