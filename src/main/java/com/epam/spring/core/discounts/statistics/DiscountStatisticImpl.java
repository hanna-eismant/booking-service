package com.epam.spring.core.discounts.statistics;

import com.epam.spring.core.events.Event;
import com.epam.spring.core.shared.Statistic;
import com.epam.spring.core.shared.StatisticDAO;
import com.epam.spring.core.users.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

@Aspect
public class DiscountStatisticImpl implements DiscountStatistic {

    private static final String TOTAL_DISCOUNT_NAME = "total discount";
    private static final String TEN_TICKET_DISCOUNT_NAME = "ten ticket discount";
    private static final String BIRTHDAY_DISCOUNT_NAME = "birthday discount";

    @Autowired
    private StatisticDAO statisticDAO;

    @Override
    @AfterReturning("execution(* com.epam.spring.core.discounts.strategies.DiscountStrategy.calculate(..))")
    public void countTotal(JoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getTarget().getClass();
        statisticDAO.incrementCounter(TOTAL_DISCOUNT_NAME, clazz.getSimpleName());
    }

    @Override
    @Before(value = "execution(* com.epam.spring.core.discounts.strategies.DiscountStrategyTenTicketImpl.calculate(..)) && args(user,event,date)", argNames = "user,event,date")
    public void countTenTicket(User user, Event event, LocalDateTime date) {
        statisticDAO.incrementCounter(TEN_TICKET_DISCOUNT_NAME, user.name);
    }


    @Override
    @AfterReturning(value = "execution(* com.epam.spring.core.discounts.strategies.DiscountStrategyBirthdayImpl.calculate(..))&& args(user,event,date)", argNames = "user,event,date")
    public void countBirthday(User user, Event event, LocalDateTime date) {
        statisticDAO.incrementCounter(BIRTHDAY_DISCOUNT_NAME, user.name);
    }


    @Override
    public Long getTotalStatistic(Class<?> strategy) {
        Statistic statistic = statisticDAO.findByNameAndType(TOTAL_DISCOUNT_NAME, strategy.getSimpleName());
        return statistic.counter;
    }

    @Override
    public Long getTenTicketStatistic(User user) {
        Statistic statistic = statisticDAO.findByNameAndType(TEN_TICKET_DISCOUNT_NAME, user.name);
        return statistic.counter;
    }

    @Override
    public Long getBirthdayStatistic(User user) {
        Statistic statistic = statisticDAO.findByNameAndType(BIRTHDAY_DISCOUNT_NAME, user.name);
        return statistic.counter;
    }
}
