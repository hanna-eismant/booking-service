package com.epam.spring.core.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventFacade eventFacade;

    @RequestMapping(method = GET)
    public ModelAndView get() {
        List<Event> events = eventFacade.getAllEventsInfo();

        ModelAndView view = new ModelAndView("events_list");
        view.addObject("events", events);

        return view;
    }

}
