package com.epam.spring.core.api.rest;


import com.epam.spring.core.shared.BookingFacade;
import com.epam.spring.core.shared.exceptions.NotEnoughMoneyException;
import com.epam.spring.core.shared.exceptions.NotFoundException;
import com.epam.spring.core.shared.exceptions.TicketAlreadyBookedException;
import com.epam.spring.core.tickets.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(path = "/api/rest")
public class BookingController {

    @Autowired
    private BookingFacade bookingFacade;

    @RequestMapping(path = "/book", method = POST, produces = "application/pdf")
    @ResponseBody
    public Ticket bookTicket(@RequestBody Map<String, Object> requestModel) throws TicketAlreadyBookedException, NotEnoughMoneyException, NotFoundException {
        String userName = (String) requestModel.get("userName");
        String ticketIdStr = (String) requestModel.get("ticketId");
        Long ticketId = Long.parseLong(ticketIdStr);

        Ticket ticket = bookingFacade.bookTicket(userName, ticketId);

        return ticket;
    }
}
