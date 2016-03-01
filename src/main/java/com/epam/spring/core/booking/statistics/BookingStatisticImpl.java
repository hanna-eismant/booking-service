package com.epam.spring.core.booking.statistics;

import org.aspectj.lang.annotation.Aspect;

@Aspect
public class BookingStatisticImpl implements BookingStatistic {

    private static final String BOOKED_NAME = "booked";
    private static final String PRICE_QUERIED_NAME = "price queried";

    // @Autowired
    // private StatisticDAO statisticDAO;
    //
    // @Override
    // @AfterReturning(value = "execution(* com.epam.spring.core.booking.BookingService*.bookTicket(..)) && args(user,ticket)", argNames = "user,ticket")
    // public void countBooked(User user, Ticket ticket) {
    // // todo: change after refactoring data model
    //// Event event = ticket.event;
    ////
    //// statisticDAO.incrementCounter(BOOKED_NAME, event.name);
    ////
    //// // if we book ticket then call getTicketPrice() within bookTicket()
    //// // therefore we increment priceQueriedCounter here
    //// statisticDAO.incrementCounter(PRICE_QUERIED_NAME, event.name);
    // }
    //
    // @Override
    // @Around(value = "execution(* com.epam.spring.core.booking.BookingService*.getTicketPrice(..)) && args(event,date,seat,user)", argNames =
    // "joinPoint,event,date,seat,user")
    // public Object countPriceQueried(ProceedingJoinPoint joinPoint, Event event, LocalDateTime date, Integer seat, User user) throws Throwable {
    //// statisticDAO.incrementCounter(PRICE_QUERIED_NAME, event.getName());
    //// return joinPoint.proceed(new Object[]{event, date, seat, user});
    // }
    //
    // @Override
    // public Long getBookedStatistic(Event event) {
    //// Statistic statistic = statisticDAO.findByNameAndType(BOOKED_NAME, event.getName());
    //// return statistic.getCounter();
    // }
    //
    // @Override
    // public Long getPriceQueriedStatistic(Event event) {
    //// Statistic statistic = statisticDAO.findByNameAndType(PRICE_QUERIED_NAME, event.getName());
    //// return statistic.getCounter();
    // }
}
