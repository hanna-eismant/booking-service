package com.epam.spring.core.tickets;

import com.epam.spring.core.shared.BookingFacade;
import com.epam.spring.core.shared.exceptions.NotEnoughMoneyException;
import com.epam.spring.core.shared.exceptions.NotFoundException;
import com.epam.spring.core.shared.exceptions.TicketAlreadyBookedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private BookingFacade bookingFacade;

    @RequestMapping(value = "/{userName}/user_tickets.pdf", method = GET)
    public ModelAndView getUserTicketsPdf(@PathVariable("userName") String userName) throws NotFoundException {
        Map<String, Object> userTickets = bookingFacade.getUserTickets(userName);

        ModelAndView view = new ModelAndView("userTicketsPdfView");
        view.addAllObjects(userTickets);

        return view;
    }

    @RequestMapping(value = "/{showId}/event_tickets.pdf", method = GET)
    public ModelAndView getEventTicketsPdf(@PathVariable("showId") Long showId) throws NotFoundException {
        Map<String, Object> eventTickets = bookingFacade.getEventTickets(showId);

        ModelAndView view = new ModelAndView("showTicketsPdfView");
        view.addAllObjects(eventTickets);

        return view;
    }

    @RequestMapping(value = "/book/{ticketId}", method = POST)
    public String book(@PathVariable("ticketId") Long ticketId, Principal principal) throws NotFoundException, TicketAlreadyBookedException, NotEnoughMoneyException {
        Ticket ticket = bookingFacade.bookTicket(principal.getName(), ticketId);
        return "redirect:/events/shows/" + ticket.getShow().getId();
    }
}
