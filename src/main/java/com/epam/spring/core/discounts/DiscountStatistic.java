package com.epam.spring.core.discounts;

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
public class DiscountStatistic {

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


    @AfterReturning("execution(* com.epam.spring.core.discounts.DiscountStrategy*.calculate(..))")
    public void countTotal(JoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getTarget().getClass();

        if (!totalCounter.containsKey(clazz)) {
            totalCounter.put(clazz, 0);
        }

        totalCounter.put(clazz, totalCounter.get(clazz) + 1);
    }

    @Before(value = "execution(* com.epam.spring.core.discounts.DiscountStrategyTenTicketImpl.calculate(..)) && args(user,event,date)", argNames = "user,event,date")
    public void countTenTicket(User user, Event event, LocalDateTime date) {
        if (!tenTicketCounter.containsKey(user)) {
            tenTicketCounter.put(user, 0);
        }

        tenTicketCounter.put(user, tenTicketCounter.get(user) + 1);
    }


    @AfterReturning(value = "execution(* com.epam.spring.core.discounts.DiscountStrategyBirthdayImpl.calculate(..))&& args(user,event,date)", argNames = "user,event,date")
    public void countBirthday(User user, Event event, LocalDateTime date) {
        if (!birthdayCounter.containsKey(user)) {
            birthdayCounter.put(user, 0);
        }

        birthdayCounter.put(user, birthdayCounter.get(user) + 1);
    }

    public void printTotalStatistic() {
        System.out.println("Total statistic:");

        for (Class<?> strategy : totalCounter.keySet()) {
            System.out.println(strategy + ": " + totalCounter.get(strategy));
        }

    }

    public void printTenTicketStatistic() {
        System.out.println("Ten Ticket statistic:");

        for (User user : tenTicketCounter.keySet()) {
            System.out.println(user + ": " + tenTicketCounter.get(user));
        }

    }

    public void printBirthdayStatistic() {
        System.out.println("Birthday statistic:");

        for (User user : birthdayCounter.keySet()) {
            System.out.println(user + ": " + birthdayCounter.get(user));
        }
    }

    public void print() {
        printTotalStatistic();
        System.out.println();
        printTenTicketStatistic();
        System.out.println();
        printBirthdayStatistic();
    }
}
