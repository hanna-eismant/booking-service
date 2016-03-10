package com.epam.spring.core.api.soap;

import com.epam.spring.core.shared.BookingFacade;
import com.epam.spring.core.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.List;

@Endpoint
public class EventsEndpoint {

    private static final String NAMESPACE_URI = "http://epam.com/spring/core/api/soap";

    @Autowired
    private BookingFacade bookingFacade;

    private ObjectFactory objectFactory = new ObjectFactory();


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventsRequest")
    @ResponsePayload
    public GetEventsResponse getEvents() {
        List<SoapEvent> soapEvents = bookingFacade.getAllSoapEvents();

        GetEventsResponse response = objectFactory.createGetEventsResponse();
        response.getEventsList().addAll(soapEvents);

        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getEventRequest")
    @ResponsePayload
    public GetEventResponse getEvent(@RequestPayload GetEventRequest request) throws NotFoundException {
        Long eventId = request.getId();
        SoapEvent event = bookingFacade.getSoapEvent(eventId);

        GetEventResponse response = objectFactory.createGetEventResponse();
        response.setEvent(event);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getShowRequest")
    @ResponsePayload
    public GetShowResponse getShow(@RequestPayload GetShowRequest request) throws NotFoundException {
        Long showId = request.getId();
        SoapShow show = bookingFacade.getSoapShow(showId);

        GetShowResponse response = objectFactory.createGetShowResponse();
        response.setShow(show);

        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createEventRequest")
    @ResponsePayload
    public CreateEventResponse createEvent(@RequestPayload CreateEventRequest request) {
        CreateEventResponse response = objectFactory.createCreateEventResponse();
        try {
            bookingFacade.createEvent(request.getName(), request.getBasePrice(), request.getRating());
            response.setStatus(true);
        } catch (Exception e) {
            response.setStatus(false);
            response.setMessage(e.getMessage());
            e.printStackTrace();
        }

        return response;
    }
}
