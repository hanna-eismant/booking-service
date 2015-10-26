package com.epam.spring.core.discounts;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.users.User;

import java.util.Date;
import java.util.List;

public class DiscountServiceImpl implements DiscountService {

    private List<DiscountStrategy> discountStrategies;

    public DiscountServiceImpl() {
    }

    public DiscountServiceImpl(List<DiscountStrategy> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }

    @Override
    public Double getDiscount(User user, Event event, Date date) {
        Double result = 0.0;

        for (DiscountStrategy strategy : discountStrategies) {
            result += strategy.calculate(user, event, date);
        }

        return result;
    }
}
