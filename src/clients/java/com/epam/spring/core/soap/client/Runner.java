package com.epam.spring.core.soap.client;

import com.epam.spring.core.soap.client.gen.CreateEventResponse;
import com.epam.spring.core.soap.client.gen.GetEventResponse;
import com.epam.spring.core.soap.client.gen.GetEventsResponse;
import com.epam.spring.core.soap.client.gen.GetShowResponse;
import com.epam.spring.core.soap.client.gen.GetUserInfoResponse;
import com.epam.spring.core.soap.client.gen.GetUsersResponse;
import com.epam.spring.core.soap.client.gen.RegisterUserResponse;
import org.joda.time.LocalDate;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Runner {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        WebServiceClient webServiceClient = context.getBean(WebServiceClient.class);

        // get all users
        System.out.println("*** all users");
        System.out.println("-------------");
        GetUsersResponse users = webServiceClient.getAllUsers();
        users.getUsersList().forEach(soapUser -> {
            System.out.println("id:    " + soapUser.getId());
            System.out.println("name:  " + soapUser.getName());
            System.out.println("email: " + soapUser.getEmail());
            System.out.println("pass:  " + soapUser.getPassword());
            System.out.println("money: " + soapUser.getAccount().getMoney());
            System.out.println();
        });

        // create new user
        RegisterUserResponse registerUser = webServiceClient.registerUser("test", "test@mail", "pass", LocalDate.parse("1987-02-02"));
        System.out.println("*** register user");
        System.out.println("-----------------");
        System.out.println("status:  " + registerUser.isStatus());
        System.out.println("message: " + registerUser.getMessage());
        System.out.println();

        // get single user's info
        GetUserInfoResponse testUser = webServiceClient.getUserByName("test");
        System.out.println("*** single user");
        System.out.println("---------------");
        System.out.println("id:    " + testUser.getUser().getId());
        System.out.println("name:  " + testUser.getUser().getName());
        System.out.println("email: " + testUser.getUser().getEmail());
        System.out.println("pass:  " + testUser.getUser().getPassword());
        System.out.println("money: " + testUser.getUser().getAccount().getMoney());
        System.out.println();




        // get all events
        System.out.println("*** all events");
        System.out.println("--------------");
        GetEventsResponse events = webServiceClient.getAllEvents();
        events.getEventsList().forEach(soapEvent -> {
            System.out.println("id:           " + soapEvent.getId());
            System.out.println("name:         " + soapEvent.getName());
            System.out.println("price:        " + soapEvent.getBasePrice());
            System.out.println("rating:       " + soapEvent.getRating());
            System.out.println("shows amount: " + soapEvent.getShowsList().size());
            System.out.println();
        });

        // get single event
        GetEventResponse event = webServiceClient.getEvent(1L);
        System.out.println("*** single event");
        System.out.println("----------------");
        System.out.println("id:           " + event.getEvent().getId());
        System.out.println("name:         " + event.getEvent().getName());
        System.out.println("price:        " + event.getEvent().getBasePrice());
        System.out.println("rating:       " + event.getEvent().getRating());
        System.out.println("shows amount: " + event.getEvent().getShowsList().size());
        System.out.println();

        // get single show
        GetShowResponse show = webServiceClient.getShow(1L);
        System.out.println("*** single show");
        System.out.println("---------------");
        System.out.println("id:           " + show.getShow().getId());
        System.out.println("event id:     " + show.getShow().getEventId());
        System.out.println("auditorium:   " + show.getShow().getAuditorium());
        System.out.println("date:         " + show.getShow().getDate());
        System.out.println("free tickets: " + show.getShow().getFreeTicketCount());
        System.out.println();

        //create event
        CreateEventResponse eventResponse = webServiceClient.createEvent("Test Event", 12000.0, "LOW");
        System.out.println("*** create event");
        System.out.println("-----------------");
        System.out.println("status:  " + eventResponse.isStatus());
        System.out.println("message: " + eventResponse.getMessage());
        System.out.println();
    }

}
