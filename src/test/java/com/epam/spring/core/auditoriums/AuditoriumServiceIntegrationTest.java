package com.epam.spring.core.auditoriums;

import com.epam.spring.core.AbstractIntegrationTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AuditoriumServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private AuditoriumService auditoriumService;

    @Test
    public void testGetAuditoriums() {
        List<Auditorium> auditoriums = auditoriumService.getAuditoriums();

        assertNotNull(auditoriums);
        assertEquals("Auditorium list has incorrect size", 2, auditoriums.size());
    }
}
