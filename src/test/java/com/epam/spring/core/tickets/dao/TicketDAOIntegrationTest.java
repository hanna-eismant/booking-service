package com.epam.spring.core.tickets.dao;

import com.epam.spring.core.AbstractIntegrationTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class TicketDAOIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    TicketDAO ticketDAO;

    @Before
    public void setUp() {
        //Ticket ticket = new Ticket(date,  event, seat, isVip, basePrice);
    }


    @After
    public void tearDown() {

    }

    @Test
    public void testCreate() {
//        ticketDAO.create();
    }

    @Test
    public void testUpdate() {

    }

    @Test
    public void testFindByEvent() {

    }

    @Test
    public void testFindByUser() {

    }
}
