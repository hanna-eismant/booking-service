package com.epam.spring.core.auditoriums.services;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.auditoriums.Auditorium;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static com.epam.spring.core.TestConstants.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AuditoriumServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private AuditoriumService auditoriumService;

    @Test
    public void testGetAuditoriums() {
        List<Auditorium> auditoriums = auditoriumService.getAuditoriums();

        assertNotNull(auditoriums);
        assertEquals(2, auditoriums.size());

        Auditorium auditoriumOne = auditoriums.get(0);
        assertNotNull(auditoriumOne);
        assertEquals(AUDITORIUM_ONE_NAME, auditoriumOne.name);
        assertEquals(AUDITORIUM_ONE_SEATS, auditoriumOne.seats);
        assertNotNull(AUDITORIUM_ONE_VIPSEATS);
        assertEquals(AUDITORIUM_ONE_VIPSEATS.size(), auditoriumOne.getVipSeats().size());

        Auditorium auditoriumTwo = auditoriums.get(1);
        assertNotNull(auditoriumTwo);
        assertEquals(AUDITORIUM_TWO_NAME, auditoriumTwo.name);
        assertEquals(AUDITORIUM_TWO_SEATS, auditoriumTwo.seats);
        assertNotNull(AUDITORIUM_TWO_VIPSEATS);
        assertEquals(AUDITORIUM_TWO_VIPSEATS.size(), auditoriumTwo.getVipSeats().size());
    }
}
