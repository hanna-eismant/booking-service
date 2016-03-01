package com.epam.spring.core.discounts.strategies;

import static org.assertj.core.api.Assertions.assertThat;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;

import com.epam.spring.core.users.User;

public class DiscountStrategyBirthdayUnitTest {

    private DiscountStrategyBirthdayImpl discountStrategyBirthday;

    @Before
    public void setUp() {
        discountStrategyBirthday = new DiscountStrategyBirthdayImpl();
    }

    @Test
    public void testCalculate() throws Exception {
        User userEq = new User();
        userEq.setBirthday(LocalDate.parse("1990-06-06"));
        Double calculateEq = discountStrategyBirthday.calculate(userEq, null, LocalDateTime.parse("2015-06-06"));
        assertThat(calculateEq).as("Discount for birthday ticket should be 5%").isEqualTo(0.05);

        User userNoEq = new User();
        userNoEq.setBirthday(LocalDate.parse("1990-06-07"));
        Double calculateNoEq = discountStrategyBirthday.calculate(userNoEq, null, LocalDateTime.parse("2015-06-06"));
        assertThat(calculateNoEq).as("Discount for non birthday ticket should be 0").isEqualTo(0.0);
    }
}
