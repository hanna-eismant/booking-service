package com.epam.spring.core.discounts.statistics;

import com.epam.spring.core.AbstractStatisticIntegrationTest;
import com.epam.spring.core.discounts.strategies.DiscountStrategy;
import com.epam.spring.core.discounts.strategies.DiscountStrategyBirthdayImpl;
import com.epam.spring.core.discounts.strategies.DiscountStrategyTenTicketImpl;
import com.epam.spring.core.users.User;
import org.junit.Test;

import static com.epam.spring.core.TestUtils.*;
import static org.junit.Assert.*;

public class DiscountStatisticIntegrationTest extends AbstractStatisticIntegrationTest {

    @Test
    public void testCountTotal() {
        Integer discountBirthdayStatistic = discountStatistic.getTotalStatistic(DiscountStrategyBirthdayImpl.class);
        assertNotNull(discountBirthdayStatistic);
        assertEquals(totalBookedTickets, discountBirthdayStatistic);

        Integer discountTenTicketStatistic = discountStatistic.getTotalStatistic(DiscountStrategyTenTicketImpl.class);
        assertNotNull(discountTenTicketStatistic);
        assertEquals(totalBookedTickets, discountTenTicketStatistic);

        Integer unknownDiscountStatistic = discountStatistic.getTotalStatistic(DiscountStrategy.class);
        assertNull(unknownDiscountStatistic);
    }

    @Test
    public void testCountTenTicket() {
        Integer jhonStatistic = discountStatistic.getTenTicketStatistic(USER_JHON);
        assertNotNull(jhonStatistic);
        assertEquals(jhonBookedTickets, jhonStatistic);

        Integer janeStatistic = discountStatistic.getTenTicketStatistic(USER_JANE);
        assertNotNull(janeStatistic);
        assertEquals(janeBookedTickets, janeStatistic);

        Integer unknownUserStatistic = discountStatistic.getTenTicketStatistic(new User());
        assertNull(unknownUserStatistic);
    }

    @Test
    public void testCountBirthday() {
        Integer jhonStatistic = discountStatistic.getBirthdayStatistic(USER_JHON);
        assertNotNull(jhonStatistic);
        assertEquals(jhonBookedTickets, jhonStatistic);

        Integer janeStatistic = discountStatistic.getBirthdayStatistic(USER_JANE);
        assertNotNull(janeStatistic);
        assertEquals(janeBookedTickets, janeStatistic);

        Integer unknownUserStatistic = discountStatistic.getBirthdayStatistic(new User());
        assertNull(unknownUserStatistic);
    }
}
