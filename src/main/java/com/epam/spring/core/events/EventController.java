package com.epam.spring.core.events;

import com.epam.spring.core.shared.BookingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private BookingFacade bookingFacade;

    @RequestMapping(method = GET)
    public ModelAndView get() {
        List<Event> events = bookingFacade.getAllEventsInfo();

        ModelAndView view = new ModelAndView("events_list");
        view.addObject("events", events);

        return view;
    }

    @RequestMapping(value = "/{eventId}", method = GET)
    public ModelAndView get(@PathVariable("eventId") Long eventId) {

        ModelAndView view = new ModelAndView("events_single");

        return view;

    }
}
