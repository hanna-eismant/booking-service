package com.epam.spring.core.discounts;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.users.User;

import java.util.Calendar;
import java.util.Date;

public class DiscountStrategyBirthdayImpl implements DiscountStrategy {

    @Override
    public Double calculate(User user, Event event, Date date) {
        // todo: check parameters

        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date);
        int eventDay = calendar.get(Calendar.DAY_OF_MONTH);
        int eventMonth = calendar.get(Calendar.MONTH);

        calendar.setTime(user.birthday);
        int userDay = calendar.get(Calendar.DAY_OF_MONTH);
        int userMonth = calendar.get(Calendar.MONTH);

        if ((eventDay == userDay) && (eventMonth == userMonth)) {
            return 0.5;
        } else {
            return 0.0;
        }
    }
}
