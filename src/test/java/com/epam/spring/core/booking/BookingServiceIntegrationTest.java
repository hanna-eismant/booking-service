package com.epam.spring.core.booking;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.tickets.Ticket;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.epam.spring.core.TestConstants.TICKET;
import static com.epam.spring.core.TestConstants.USER_JHON;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private BookingService bookingService;

    @Test
    public void bookTicket() {
        Double finalPrice = 19_200d;
        Double totalMoney = 100_000d;

        Ticket ticket = bookingService.bookTicket(USER_JHON, TICKET);

        assertThat(ticket).as("returned ticked cannot be 'null'").isNotNull();

        assertThat(ticket.getUser()).as("Ticket should be assigned for user").isNotNull();
        assertThat(ticket.getUser().getId()).as("Ticket is assigned to another user").isEqualTo(USER_JHON.getId());

        assertThat(ticket.getDiscountPrice()).as("Ticket has incorrect final price").isEqualTo(finalPrice);

        assertThat(USER_JHON.getAccount().getMoney()).as("It is withdraw incorrect price from user account")
                .isEqualTo(totalMoney - finalPrice);
    }
}
