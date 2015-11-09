package com.epam.spring.core.discounts.strategies;

import com.epam.spring.core.users.User;
import com.epam.spring.core.users.services.UserService;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DiscountStrategyTenTicketUnitTest {

    public static final String EVENT_DATE = "2015-06-06T14:00:00.000";

    private DiscountStrategyTenTicketImpl discountStrategyTenTicket;
    private UserService userService;

    private User user;

    private void createStubUser() {
        user = new User();
        user.name = "user";
        user.email = "user@mail";
    }

    @Before
    public void setUp() {
        discountStrategyTenTicket = new DiscountStrategyTenTicketImpl();
        userService = mock(UserService.class);
        discountStrategyTenTicket.setUserService(userService);

        createStubUser();
    }

    @Test
    public void testCalculate() {
        // test not 10th ticket
        when(userService.getBookedTicketsCount(user)).thenReturn(5);
        Double discount = discountStrategyTenTicket.calculate(user, null, LocalDateTime.parse(EVENT_DATE));
        Double expect = 0.0;
        assertEquals(expect, discount);

        // test 10th ticket
        when(userService.getBookedTicketsCount(user)).thenReturn(9);
        discount = discountStrategyTenTicket.calculate(user, null, LocalDateTime.parse(EVENT_DATE));
        expect = 0.5;
        assertEquals(expect, discount);
    }
}
