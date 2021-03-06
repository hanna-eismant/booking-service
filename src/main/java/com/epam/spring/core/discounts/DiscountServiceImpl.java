package com.epam.spring.core.discounts;

import com.epam.spring.core.discounts.strategies.DiscountStrategy;
import com.epam.spring.core.events.Event;
import com.epam.spring.core.users.User;
import org.joda.time.LocalDateTime;

import java.util.List;

public class DiscountServiceImpl implements DiscountService {

    private List<DiscountStrategy> discountStrategies;

    public DiscountServiceImpl() {
    }

    public DiscountServiceImpl(List<DiscountStrategy> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }

    @Override
    public Double getDiscount(User user, Event event, LocalDateTime date) {
        Double result = 0.0;

        for (DiscountStrategy strategy : discountStrategies) {
            result += strategy.calculate(user, event, date);
        }

        return result;
    }
}
