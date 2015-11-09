package com.epam.spring.core.discounts.strategies;

import com.epam.spring.core.users.User;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DiscountStrategyBirthdayUnitTest {

    private DiscountStrategyBirthdayImpl discountStrategyBirthday;

    @Before
    public void setUp() {
        discountStrategyBirthday = new DiscountStrategyBirthdayImpl();
    }

    @Test
    public void testCalculate() throws Exception {
        User userEq = new User();
        userEq.birthday = LocalDate.parse("1990-06-06");

        Double calculateEq = discountStrategyBirthday.calculate(userEq, null, LocalDateTime.parse("2015-06-06T14:00:00.000"));
        Double expectEq = 0.05;
        assertEquals(expectEq, calculateEq);

        User userNoEq = new User();
        userNoEq.birthday = LocalDate.parse("1990-06-07");

        Double calculateNoEq = discountStrategyBirthday.calculate(userNoEq, null, LocalDateTime.parse("2015-06-06"));
        Double expectNoEq = 0.0;
        assertEquals(expectNoEq, calculateNoEq);
    }
}
