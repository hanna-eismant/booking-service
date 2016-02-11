package com.epam.spring.core.tickets;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/tickets")
public class TicketController {

    @RequestMapping(value = "/{userName}/tickets.pdf", method = GET)
    public ModelAndView getPdf(@PathVariable("userName") String userName) {
        ModelAndView view = new ModelAndView("userTicketsPdfView");
        view.addObject("userName", userName);

        return view;
    }
}
