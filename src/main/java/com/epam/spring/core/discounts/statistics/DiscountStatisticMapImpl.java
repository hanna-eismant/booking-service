package com.epam.spring.core.discounts.statistics;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.users.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.joda.time.LocalDateTime;

import java.util.HashMap;
import java.util.Map;

@Aspect
public class DiscountStatisticMapImpl implements DiscountStatistic {

    /**
     * How many times each discount was given total
     */
    private Map<Class<?>, Integer> totalCounter = new HashMap<>();

    /**
     * How many times each discount for 10th ticket was given for specific user
     */
    private Map<User, Integer> tenTicketCounter = new HashMap<>();

    /**
     * How many times each discount for birthday was given for specific user
     */
    private Map<User, Integer> birthdayCounter = new HashMap<>();


    @Override
    @AfterReturning("execution(* com.epam.spring.core.discounts.strategies.DiscountStrategy.calculate(..))")
    public void countTotal(JoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getTarget().getClass();

        if (!totalCounter.containsKey(clazz)) {
            totalCounter.put(clazz, 0);
        }

        totalCounter.put(clazz, totalCounter.get(clazz) + 1);
    }

    @Override
    @Before(value = "execution(* com.epam.spring.core.discounts.strategies.DiscountStrategyTenTicketImpl.calculate(..)) && args(user,event,date)", argNames = "user,event,date")
    public void countTenTicket(User user, Event event, LocalDateTime date) {
        if (!tenTicketCounter.containsKey(user)) {
            tenTicketCounter.put(user, 0);
        }

        tenTicketCounter.put(user, tenTicketCounter.get(user) + 1);
    }


    @Override
    @AfterReturning(value = "execution(* com.epam.spring.core.discounts.strategies.DiscountStrategyBirthdayImpl.calculate(..))&& args(user,event,date)", argNames = "user,event,date")
    public void countBirthday(User user, Event event, LocalDateTime date) {
        if (!birthdayCounter.containsKey(user)) {
            birthdayCounter.put(user, 0);
        }

        birthdayCounter.put(user, birthdayCounter.get(user) + 1);
    }


    @Override
    public Integer getTotalStatistic(Class<?> strategy) {
        if (totalCounter.containsKey(strategy)) {
            return totalCounter.get(strategy);
        }
        return null;
    }

    @Override
    public Integer getTenTicketStatistic(User user) {
        if (tenTicketCounter.containsKey(user)) {
            return tenTicketCounter.get(user);
        }
        return null;
    }

    @Override
    public Integer getBirthdayStatistic(User user) {
        if (birthdayCounter.containsKey(user)) {
            return birthdayCounter.get(user);
        }
        return null;
    }

    @Override
    public void removeAll() {
        totalCounter.clear();
        tenTicketCounter.clear();
        birthdayCounter.clear();
    }
}
