package com.epam.spring.core.auditoriums.services;

import com.epam.spring.core.AbstractIntegrationTest;
import com.epam.spring.core.auditoriums.Auditorium;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AuditoriumServiceIntegrationTest extends AbstractIntegrationTest {

    // data from test properties files

    private String auditoriumOneName = "auditorium test one";
    private Integer auditoriumOneSseats = 24;
    private List<Integer> auditoriumOneVipseats = new ArrayList<>(Arrays.asList(4, 5, 6, 7, 8, 9, 10));

    private String auditoriumTwoName = "auditorium test two";
    private Integer auditoriumTwoSeats = 12;
    private List<Integer> auditoriumTwoVipseats = new ArrayList<>(Collections.singletonList(2));

    @Autowired
    private AuditoriumService auditoriumService;

    @Test
    public void testGetAuditoriums() {
        List<Auditorium> auditoriums = auditoriumService.getAuditoriums();

        assertNotNull(auditoriums);
        assertEquals(2, auditoriums.size());
    }
}
