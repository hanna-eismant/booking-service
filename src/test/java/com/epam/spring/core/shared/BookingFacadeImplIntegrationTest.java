package com.epam.spring.core.shared;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.shared.exceptions.NotFoundException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

import static com.epam.spring.core.TestConstants.JANE_BOOKED_COUNT;
import static com.epam.spring.core.TestConstants.USER_JANE;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingFacadeImplIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private BookingFacade bookingFacade;

    //
    // @Test
    // public void getAllEventsInfo() {
    //
    // }
    //
    // @Test
    // public void getEventInfo() {
    //
    // }

    @Test
    public void getUserInfo() throws NotFoundException {
        Map<String, Object> userInfo = bookingFacade.getUserInfo(USER_JANE.getName());

        assertThat(userInfo).as("User info cannot be 'null'").isNotNull();
        assertThat(userInfo).as("User info contains incorrect parameters count").hasSize(5);

        assertThat(userInfo).containsEntry("name", USER_JANE.getName());
        assertThat(userInfo).containsEntry("email", USER_JANE.getEmail());
        assertThat(userInfo).containsEntry("birthday", USER_JANE.getBirthday());
        assertThat(userInfo).containsEntry("ticketsCount", JANE_BOOKED_COUNT);
        assertThat(userInfo).containsEntry("money", USER_JANE.getAccount().getMoney());
    }

    // @Test
    // public void getUser() {
    //
    // }
    //
    // @Test
    // public void getAllUsersInfo() {
    //
    // }
    //
    // @Test
    // public void getUserTickets() {
    //
    // }
    //
    // @Test
    // public void getEventTickets() {
    //
    // }
}
