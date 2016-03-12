package com.epam.spring.core.api.rest;

import com.epam.spring.core.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static com.epam.spring.core.TestConstants.TICKET_FREE;
import static com.epam.spring.core.TestConstants.USER_JHON;

public class BookingControllerIntegrationTest extends AbstractIntegrationTest {

    public static final String REST_URL = "http://localhost:8080/api/rest/book";

    @Test
    public void testBookTicket() {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> vars = new HashMap<>();

        vars.put("ticketId", TICKET_FREE.getId().toString());
        vars.put("userName", USER_JHON.getName());

        restTemplate.postForLocation(REST_URL, vars, new HashMap<>());
    }
}
