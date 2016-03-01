package com.epam.spring.core.booking;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.shared.exceptions.NotEnoughMoneyException;
import com.epam.spring.core.shared.exceptions.NotFoundException;
import com.epam.spring.core.shared.exceptions.TicketAlreadyBookedException;
import com.epam.spring.core.tickets.Ticket;
import com.epam.spring.core.tickets.TicketService;
import com.epam.spring.core.users.User;
import com.epam.spring.core.users.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static com.epam.spring.core.TestConstants.JHON_BOOKED_COUNT;
import static com.epam.spring.core.TestConstants.JANE_BOOKED_COUNT;
import static com.epam.spring.core.TestConstants.TICKET_FREE;
import static com.epam.spring.core.TestConstants.TICKET_JANE;
import static com.epam.spring.core.TestConstants.TICKET_JHON;
import static com.epam.spring.core.TestConstants.USER_JANE;
import static com.epam.spring.core.TestConstants.USER_JHON;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class BookingServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @Test
    public void bookTicket() throws NotFoundException, TicketAlreadyBookedException, NotEnoughMoneyException {
        Double finalPrice = TICKET_FREE.getBasePrice();
        Double totalMoney = USER_JHON.getAccount().getMoney();

        bookingService.bookTicket(USER_JHON.getName(), TICKET_FREE.getId());

        // get all information to check from db

        Long bookedTicketsCount = ticketService.getBookedTicketsCount(USER_JHON);
        assertThat(bookedTicketsCount).isEqualTo(JHON_BOOKED_COUNT + 1);

        User user = userService.getById(USER_JHON.getId());
        assertThat(user.getAccount().getMoney()).as("It is withdraw incorrect price from user account").isEqualTo(totalMoney - finalPrice);

        Ticket ticket = ticketService.getById(TICKET_FREE.getId());
        assertThat(ticket).as("Returned ticked cannot be 'null'").isNotNull();
        assertThat(ticket.getUser()).as("Ticket should be assigned for user").isNotNull();
        assertThat(ticket.getUser().getId()).as("Ticket is assigned to another user").isEqualTo(USER_JHON.getId());
        assertThat(ticket.getDiscountPrice()).as("Ticket has incorrect final price").isEqualTo(finalPrice);
    }

    @Test
    public void bookTicket_NotEnoughMoney() throws NotFoundException {
        Double totalMoney = USER_JANE.getAccount().getMoney();
        assertThatExceptionOfType(NotEnoughMoneyException.class).isThrownBy(() -> bookingService.bookTicket(USER_JANE.getName(), TICKET_FREE.getId()));

        Long bookedTicketsCount = ticketService.getBookedTicketsCount(USER_JANE);
        assertThat(bookedTicketsCount).isEqualTo(JANE_BOOKED_COUNT);

        User user = userService.getById(USER_JANE.getId());
        assertThat(user.getAccount().getMoney()).as("Failed booking should not withdraw any price from user account").isEqualTo(totalMoney);

        Ticket ticket = ticketService.getById(TICKET_FREE.getId());
        assertThat(ticket).as("Returned ticked cannot be 'null'").isNotNull();
        assertThat(ticket.getUser()).as("Ticket should be not assigned for user").isNull();
        assertThat(ticket.getDiscountPrice()).as("Ticket should be not have final price").isNull();
    }

    @Test
    public void bookTicket_Duplicate_SameUser() throws NotFoundException, TicketAlreadyBookedException, NotEnoughMoneyException {
        Double totalMoney = USER_JHON.getAccount().getMoney();
        bookingService.bookTicket(USER_JHON.getName(), TICKET_JHON.getId());

        // get all information to check from db

        Long bookedTicketsCount = ticketService.getBookedTicketsCount(USER_JHON);
        assertThat(bookedTicketsCount).isEqualTo(JHON_BOOKED_COUNT);

        User user = userService.getById(USER_JHON.getId());
        assertThat(user.getAccount().getMoney()).as("Duplicate booking should not withdraw any price from user account").isEqualTo(totalMoney);

        Ticket ticket = ticketService.getById(TICKET_JHON.getId());
        assertThat(ticket).as("Returned ticked cannot be 'null'").isNotNull();
        assertThat(ticket.getUser()).as("Ticket should be assigned for user").isNotNull();
        assertThat(ticket.getUser().getId()).as("Ticket is assigned to another user").isEqualTo(USER_JHON.getId());
        assertThat(ticket.getDiscountPrice()).as("Ticket has incorrect final price").isEqualTo(TICKET_JHON.getDiscountPrice());
    }

    @Test
    public void bookTicket_Duplicate_AnotherUser() throws NotFoundException {
        Double totalMoney = USER_JHON.getAccount().getMoney();

        assertThatExceptionOfType(TicketAlreadyBookedException.class).isThrownBy(() -> bookingService.bookTicket(USER_JHON.getName(), TICKET_JANE.getId()));

        // get all information to check from db

        Long bookedTicketsCount = ticketService.getBookedTicketsCount(USER_JHON);
        assertThat(bookedTicketsCount).isEqualTo(JHON_BOOKED_COUNT);

        User user = userService.getById(USER_JHON.getId());
        assertThat(user.getAccount().getMoney()).as("Failed booking should not withdraw any price from user account").isEqualTo(totalMoney);

        Ticket ticket = ticketService.getById(TICKET_JANE.getId());
        assertThat(ticket).as("Returned ticked cannot be 'null'").isNotNull();
        assertThat(ticket.getUser()).as("Ticket should be assigned for user").isNotNull();
        assertThat(ticket.getUser().getId()).as("Ticket is assigned to incorrect user").isEqualTo(USER_JANE.getId());
        assertThat(ticket.getDiscountPrice()).as("Ticket has incorrect final price").isEqualTo(TICKET_JANE.getDiscountPrice());
    }
}
