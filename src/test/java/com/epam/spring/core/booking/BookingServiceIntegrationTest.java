package com.epam.spring.core.booking;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.shared.NotFoundException;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.tickets.TicketService;
import com.epam.spring.core.users.User;
import com.epam.spring.core.users.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.epam.spring.core.TestConstants.JHON_BOOKED_COUNT;
import static com.epam.spring.core.TestConstants.TICKET;
import static com.epam.spring.core.TestConstants.USER_JHON;
import static org.assertj.core.api.Assertions.assertThat;

public class BookingServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Test
    public void bookTicket() throws NotFoundException {
        Double finalPrice = 19_200d;
        Double totalMoney = 100_000d;

        Ticket ticket = bookingService.bookTicket(USER_JHON, TICKET);

        Long bookedTicketsCount = ticketService.getBookedTicketsCount(USER_JHON);
        assertThat(bookedTicketsCount).isEqualTo(JHON_BOOKED_COUNT + 1);

        User user = userService.getById(USER_JHON.getId());
        assertThat(user.getAccount().getMoney()).as("It is withdraw incorrect price from user account")
                .isEqualTo(totalMoney - finalPrice);

        assertThat(ticket).as("returned ticked cannot be 'null'").isNotNull();

        assertThat(ticket.getUser()).as("Ticket should be assigned for user").isNotNull();
        assertThat(ticket.getUser().getId()).as("Ticket is assigned to another user").isEqualTo(USER_JHON.getId());

        assertThat(ticket.getDiscountPrice()).as("Ticket has incorrect final price").isEqualTo(finalPrice);
    }
}
