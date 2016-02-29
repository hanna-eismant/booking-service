package com.epam.spring.core.discounts.strategies;

import com.epam.spring.core.tickets.TicketService;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

public class DiscountStrategyTenTicketUnitTest {

    public static final String EVENT_DATE = "2015-06-06T14:00:00.000";

    private DiscountStrategyTenTicketImpl discountStrategyTenTicket;

    @Mock
    private TicketService ticketService;

    private User user;

    private void createStubUser() {
        user = new User();
        user.setName("user");
        user.setEmail("user@mail");
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        discountStrategyTenTicket = new DiscountStrategyTenTicketImpl();
        discountStrategyTenTicket.setTicketService(ticketService);

        createStubUser();
    }

    @Test
    public void testCalculate() {
        // test not 10th ticket
        when(ticketService.getBookedTicketsCount(user)).thenReturn(5L);
        Double discount = discountStrategyTenTicket.calculate(user, null, LocalDateTime.parse(EVENT_DATE));

        assertThat(discount).as("Discount for 6th ticket should be 0").isEqualTo(0.0);

        // test 10th ticket
        when(ticketService.getBookedTicketsCount(user)).thenReturn(9L);
        discount = discountStrategyTenTicket.calculate(user, null, LocalDateTime.parse(EVENT_DATE));

        assertThat(discount).as("Discount for 10th ticket should be 50%").isEqualTo(0.5);
    }
}
