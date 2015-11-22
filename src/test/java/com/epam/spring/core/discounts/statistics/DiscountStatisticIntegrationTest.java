package com.epam.spring.core.discounts.statistics;

import com.epam.spring.core.AbstractStatisticIntegrationTest;
import com.epam.spring.core.discounts.strategies.DiscountStrategy;
import com.epam.spring.core.discounts.strategies.DiscountStrategyBirthdayImpl;
import com.epam.spring.core.discounts.strategies.DiscountStrategyTenTicketImpl;
import com.epam.spring.core.users.User;
import org.junit.Test;

import static com.epam.spring.core.TestConstants.*;
import static org.junit.Assert.*;

public class DiscountStatisticIntegrationTest extends AbstractStatisticIntegrationTest {

    @Test
    public void testCountTotal() {
        Long discountBirthdayStatistic = discountStatistic.getTotalStatistic(DiscountStrategyBirthdayImpl.class);
        assertNotNull(discountBirthdayStatistic);
        assertEquals(totalBookedTickets, discountBirthdayStatistic);

        Long discountTenTicketStatistic = discountStatistic.getTotalStatistic(DiscountStrategyTenTicketImpl.class);
        assertNotNull(discountTenTicketStatistic);
        assertEquals(totalBookedTickets, discountTenTicketStatistic);

        Long unknownDiscountStatistic = discountStatistic.getTotalStatistic(DiscountStrategy.class);
        assertNotNull(unknownDiscountStatistic);
        assertEquals(Long.valueOf(0L), unknownDiscountStatistic);
    }

    @Test
    public void testCountTenTicket() {
        Long jhonStatistic = discountStatistic.getTenTicketStatistic(USER_JHON);
        assertNotNull(jhonStatistic);
        assertEquals(jhonBookedTickets, jhonStatistic);

        Long janeStatistic = discountStatistic.getTenTicketStatistic(USER_JANE);
        assertNotNull(janeStatistic);
        assertEquals(janeBookedTickets, janeStatistic);

        User absenteeUser = new User();
        absenteeUser.name = "Absentee user";

        Long unknownUserStatistic = discountStatistic.getTenTicketStatistic(absenteeUser);
        assertNotNull(unknownUserStatistic);
        assertEquals(Long.valueOf(0L), unknownUserStatistic);
    }

    @Test
    public void testCountBirthday() {
        Long jhonStatistic = discountStatistic.getBirthdayStatistic(USER_JHON);
        assertNotNull(jhonStatistic);
        assertEquals(jhonBookedTickets, jhonStatistic);

        Long janeStatistic = discountStatistic.getBirthdayStatistic(USER_JANE);
        assertNotNull(janeStatistic);
        assertEquals(janeBookedTickets, janeStatistic);

        User absenteeUser = new User();
        absenteeUser.name = "Absentee user";

        Long unknownUserStatistic = discountStatistic.getBirthdayStatistic(absenteeUser);
        assertNotNull(unknownUserStatistic);
        assertEquals(Long.valueOf(0L), unknownUserStatistic);
    }
}
