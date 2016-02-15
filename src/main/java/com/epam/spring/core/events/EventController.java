package com.epam.spring.core.events;

import com.epam.spring.core.shared.BookingFacade;
import com.epam.spring.core.shared.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

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
    public ModelAndView get(@PathVariable("eventId") Long eventId) throws NotFoundException {
        Event event = bookingFacade.getEventInfo(eventId);

        ModelAndView view = new ModelAndView("events_single");
        view.addObject("event", event);

        return view;

    }


    @RequestMapping(value = "/upload", method = POST)
    public ModelAndView upload(@RequestParam MultipartFile eventsInfoFile) throws IOException {
        InputStream inputStream = eventsInfoFile.getInputStream();
        ModelAndView view = new ModelAndView("events_upload");

        try {
            Map<String, List<Event>> result = bookingFacade.parseEvents(inputStream);
            view.addAllObjects(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

}
