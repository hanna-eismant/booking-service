package com.epam.spring.core.users;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.shared.DuplicateException;
import com.epam.spring.core.shared.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

import static com.epam.spring.core.TestConstants.USER_BIRTHDAY;
import static com.epam.spring.core.TestConstants.USER_EMAIL;
import static com.epam.spring.core.TestConstants.USER_JANE;
import static com.epam.spring.core.TestConstants.USER_JHON;
import static com.epam.spring.core.TestConstants.USER_NAME;
import static com.epam.spring.core.TestConstants.USER_PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class UserServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void register() throws DuplicateException {
        User user = userService.register(USER_NAME, USER_EMAIL, USER_PASSWORD, USER_BIRTHDAY);

        assertThat(user).as("Registered user cannot be null").isNotNull();
        assertThat(user.getId()).as("Registered user should have id").isNotNull();

        assertThat(user.getName()).as("Registered user has incorrect name").isEqualTo(USER_NAME);
        assertThat(user.getEmail()).as("Registered user has incorrect email").isEqualTo(USER_EMAIL);
        assertThat(user.getBirthday()).as("Registered user has incorrect birthday").isEqualTo(USER_BIRTHDAY);

        boolean matches = passwordEncoder.matches(USER_PASSWORD, user.getPassword());
        assertThat(matches).as("Registered user has incorrect password").isTrue();

        assertThat(user.getRoles()).as("Registered user has incorrect roles").contains(UserRoles.ROLE_REGISTERED_USER)
                .doesNotContain(UserRoles.ROLE_BOOKING_MANAGER);

        assertThat(user.getAccount()).as("Registered user should have account").isNotNull();
        assertThat(user.getAccount().getMoney()).as("User account has incorrect money amount").isEqualTo(0.0);
    }

    @Test()
    public void register_Duplicate() {
        assertThatExceptionOfType(DuplicateException.class)
                .isThrownBy(() -> userService.register(USER_JANE.getName(), USER_JANE.getEmail(), USER_PASSWORD, USER_JANE.getBirthday()));
    }

    @Test
    public void getAll() {
        List<User> all = userService.getAll();

        assertThat(all).as("Found list of users cannot be null").isNotNull();
        assertThat(all).as("Found list of users has incorrect size").hasSize(3);
        assertThat(all).as("Found list of users has incorrect users").extracting("name").contains("admin", USER_JANE.getName(), USER_JHON.getName());
    }

    @Test
    public void getById_Registered() throws NotFoundException {
        User userTest = userService.getById(USER_JANE.getId());

        assertThat(userTest).as("Found user cannot be null").isNotNull();

        assertThat(userTest.getId()).as("Found user has incorrect id").isEqualTo(USER_JANE.getId());
        assertThat(userTest.getName()).as("Found user has incorrect name").isEqualTo(USER_JANE.getName());
        assertThat(userTest.getEmail()).as("Found user has incorrect email").isEqualTo(USER_JANE.getEmail());
        assertThat(userTest.getBirthday()).as("Found user has incorrect birthday").isEqualTo(USER_JANE.getBirthday());

        boolean matches = passwordEncoder.matches(USER_PASSWORD, userTest.getPassword());
        assertThat(matches).as("Found user has incorrect password").isTrue();

        assertThat(userTest.getRoles()).as("Found user has incorrect roles").contains(UserRoles.ROLE_REGISTERED_USER)
                .doesNotContain(UserRoles.ROLE_BOOKING_MANAGER);

        assertThat(userTest.getAccount()).as("Found user should have account").isNotNull();
        assertThat(userTest.getAccount().getMoney()).as("User account has incorrect money amount")
                .isEqualTo(USER_JANE.getAccount().getMoney());
    }

    @Test
    public void getByEmail_Registered() throws NotFoundException {
        User userTest = userService.getByEmail(USER_JANE.getEmail());

        assertThat(userTest).as("Found user cannot be null").isNotNull();

        assertThat(userTest.getId()).as("Found user has incorrect id").isEqualTo(USER_JANE.getId());
        assertThat(userTest.getName()).as("Found user has incorrect name").isEqualTo(USER_JANE.getName());
        assertThat(userTest.getEmail()).as("Found user has incorrect email").isEqualTo(USER_JANE.getEmail());
        assertThat(userTest.getBirthday()).as("Found user has incorrect birthday").isEqualTo(USER_JANE.getBirthday());

        boolean matches = passwordEncoder.matches(USER_PASSWORD, userTest.getPassword());
        assertThat(matches).as("Found user has incorrect password").isTrue();

        assertThat(userTest.getRoles()).as("Found user has incorrect roles").contains(UserRoles.ROLE_REGISTERED_USER)
                .doesNotContain(UserRoles.ROLE_BOOKING_MANAGER);

        assertThat(userTest.getAccount()).as("Found user should have account").isNotNull();
        assertThat(userTest.getAccount().getMoney()).as("User account has incorrect money amount")
                .isEqualTo(USER_JANE.getAccount().getMoney());
    }

    @Test
    public void getByName_Registered() throws NotFoundException {
        User userTest = userService.getByName(USER_JANE.getName());

        assertThat(userTest).as("Found user cannot be null").isNotNull();

        assertThat(userTest.getId()).as("Found user has incorrect id").isEqualTo(USER_JANE.getId());
        assertThat(userTest.getName()).as("Found user has incorrect name").isEqualTo(USER_JANE.getName());
        assertThat(userTest.getEmail()).as("Found user has incorrect email").isEqualTo(USER_JANE.getEmail());
        assertThat(userTest.getBirthday()).as("Found user has incorrect birthday").isEqualTo(USER_JANE.getBirthday());

        boolean matches = passwordEncoder.matches(USER_PASSWORD, userTest.getPassword());
        assertThat(matches).as("Found user has incorrect password").isTrue();

        assertThat(userTest.getRoles()).as("Found user has incorrect roles").contains(UserRoles.ROLE_REGISTERED_USER)
                .doesNotContain(UserRoles.ROLE_BOOKING_MANAGER);

        assertThat(userTest.getAccount()).as("Found user should have account").isNotNull();
        assertThat(userTest.getAccount().getMoney()).as("User account has incorrect money amount")
                .isEqualTo(USER_JANE.getAccount().getMoney());
    }

    @Test
    public void getById_Unregistered() {
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> userService.getById(1_000L));
    }

    @Test
    public void getByEmail_Unregistered() {
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> userService.getByEmail("qqqqqqq"));
    }

    @Test
    public void getByName_Unregistered() {
        assertThatExceptionOfType(NotFoundException.class).isThrownBy(() -> userService.getByName("qqqqqqqq"));
    }
}
