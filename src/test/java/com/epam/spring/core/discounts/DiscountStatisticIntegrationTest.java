package com.epam.spring.core.discounts;

import com.epam.spring.core.AbstractStatisticIntegrationTest;
import com.epam.spring.core.discounts.strategies.DiscountStrategy;
import com.epam.spring.core.discounts.strategies.DiscountStrategyBirthdayImpl;
import com.epam.spring.core.discounts.strategies.DiscountStrategyTenTicketImpl;
import com.epam.spring.core.users.User;
import org.junit.Test;

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
        Integer userStatistic = discountStatistic.getTenTicketStatistic(user);
        assertNotNull(userStatistic);
        assertEquals(userBookedTickets, userStatistic);

        Integer adminStatistic = discountStatistic.getTenTicketStatistic(admin);
        assertNotNull(adminStatistic);
        assertEquals(adminBookedTickets, adminStatistic);

        Integer unknownUserStatistic = discountStatistic.getTenTicketStatistic(new User());
        assertNull(unknownUserStatistic);
    }

    @Test
    public void testCountBirthday() {
        Integer userStatistic = discountStatistic.getBirthdayStatistic(user);
        assertNotNull(userStatistic);
        assertEquals(userBookedTickets, userStatistic);

        Integer adminStatistic = discountStatistic.getBirthdayStatistic(admin);
        assertNotNull(adminStatistic);
        assertEquals(adminBookedTickets, adminStatistic);

        Integer unknownUserStatistic = discountStatistic.getBirthdayStatistic(new User());
        assertNull(unknownUserStatistic);
    }
}
