package com.epam.spring.core.discounts;

import com.epam.spring.core.users.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

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
    private Map<User, Integer> tenTicketCounter;

    /**
     * How many times each discount for birthday was given for specific user
     */
    private Map<User, Integer> birthdayCounter;


    @AfterReturning("execution(* com.epam.spring.core.discounts.DiscountStrategy*.calculate(..))")
    public void countTotal(JoinPoint joinPoint) {
        Class<?> clazz = joinPoint.getTarget().getClass();

        if (!totalCounter.containsKey(clazz)) {
            totalCounter.put(clazz, 0);
        }

        totalCounter.put(clazz, totalCounter.get(clazz) + 1);
    }

    public void printTotalStatistic() {
        System.out.println("Total statistic:");

        for (Class<?> strategy : totalCounter.keySet()) {
            System.out.println(strategy + ": " + totalCounter.get(strategy));
        }

    }
}
