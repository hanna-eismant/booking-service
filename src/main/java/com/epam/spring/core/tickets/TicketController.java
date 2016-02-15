package com.epam.spring.core.tickets;

import com.epam.spring.core.shared.BookingFacade;
import com.epam.spring.core.shared.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @Autowired
    private BookingFacade bookingFacade;

    @RequestMapping(value = "/{userName}/tickets.pdf", method = GET)
    public ModelAndView getPdf(@PathVariable("userName") String userName) throws NotFoundException {
        Map<String, Object> userTickets = bookingFacade.getUserTickets(userName);

        ModelAndView view = new ModelAndView("userTicketsPdfView");
        view.addAllObjects(userTickets);

        return view;
    }
}
