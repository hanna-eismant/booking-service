package com.epam.spring.core.tickets;

import java.util.Map;

public interface TicketFacade {

    Map<String, Object> getUserTickets(final String userName);


}
