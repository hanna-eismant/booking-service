package com.epam.spring.core.users;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.shared.DuplicateException;
import com.epam.spring.core.shared.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.epam.spring.core.TestConstants.USER_BIRTHDAY;
import static com.epam.spring.core.TestConstants.USER_EMAIL;
import static com.epam.spring.core.TestConstants.USER_JANE;
import static com.epam.spring.core.TestConstants.USER_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UserServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UserService userService;

    @Test
    public void testRegister() throws DuplicateException {
        User user = userService.register(USER_NAME, USER_EMAIL, USER_BIRTHDAY);
        assertNotNull("Registered user cannot be null", user);
        assertNotNull("Registered user should have id", user.getId());
        assertEquals("Registered user has incorrect name", user.getName(), USER_NAME);
        assertEquals("Registered user has incorrect email", user.getEmail(), USER_EMAIL);
        assertEquals("Registered user has incorrect birthday", user.getBirthday(), USER_BIRTHDAY);
    }

    @Test(expected = DuplicateException.class)
    public void testRegisterDuplicate() throws DuplicateException {
        userService.register(USER_JANE.getName(), USER_JANE.getEmail(), USER_JANE.getBirthday());
    }

    @Test
    public void testGetAll() {
        List<UserEntity> all = userService.getAll();
        assertNotNull("Found list of users cannot be null", all);
        assertEquals("Found list of users has incorrect size", 2, all.size());
    }

    @Test
    public void testGetByIdRegistered() throws NotFoundException {
        User userTest = userService.getById(USER_JANE.getId());
        assertNotNull("Found user cannot be null", userTest);
        assertEquals("Found user has incorrect id", USER_JANE.getId(), userTest.getId());
        assertEquals("Found user has incorrect name", USER_JANE.getName(), userTest.getName());
        assertEquals("Found user has incorrect email", USER_JANE.getEmail(), userTest.getEmail());
        assertEquals("Found user has incorrect birthday", USER_JANE.getBirthday(), userTest.getBirthday());
    }

    @Test
    public void testGetByEmailRegistered() throws NotFoundException {
        User userTest = userService.getByEmail(USER_JANE.getEmail());
        assertNotNull("Found user cannot be null", userTest);
        assertEquals("Found user has incorrect id", USER_JANE.getId(), userTest.getId());
        assertEquals("Found user has incorrect name", USER_JANE.getName(), userTest.getName());
        assertEquals("Found user has incorrect email", USER_JANE.getEmail(), userTest.getEmail());
        assertEquals("Found user has incorrect birthday", USER_JANE.getBirthday(), userTest.getBirthday());
    }

    @Test
    public void testGetByNameRegistered() throws NotFoundException {
        User userTest = userService.getByName(USER_JANE.getName());
        assertNotNull("Found user cannot be null", userTest);
        assertEquals("Found user has incorrect id", USER_JANE.getId(), userTest.getId());
        assertEquals("Found user has incorrect name", USER_JANE.getName(), userTest.getName());
        assertEquals("Found user has incorrect email", USER_JANE.getEmail(), userTest.getEmail());
        assertEquals("Found user has incorrect birthday", USER_JANE.getBirthday(), userTest.getBirthday());
    }

    @Test(expected = NotFoundException.class)
    public void testGetByIdUnregistered() throws NotFoundException {
        userService.getById(1_000L);
    }

    @Test(expected = NotFoundException.class)
    public void testGetByEmailUnregistered() throws NotFoundException {
        userService.getByEmail("qqqqqqq");
    }

    @Test(expected = NotFoundException.class)
    public void testGetByNameUnregistered() throws NotFoundException {
        userService.getByName("qqqqqqqq");
    }
}
