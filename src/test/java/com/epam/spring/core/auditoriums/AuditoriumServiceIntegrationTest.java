package com.epam.spring.core.auditoriums;

import static com.epam.spring.core.TestConstants.AUDITORIUM_ONE;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.epam.spring.core.AbstractIntegrationTest;

public class AuditoriumServiceIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private AuditoriumService auditoriumService;

    @Test
    public void testGetAuditoriums() {
        List<Auditorium> auditoriums = auditoriumService.getAuditoriums();

        assertThat(auditoriums).as("Auditorium list cannot be 'null'").isNotNull();
        assertThat(auditoriums).as("Auditorium list has incorrect size").hasSize(2);
        assertThat(auditoriums).as("Auditorium list contains incorrect elements").extracting("name").contains(AUDITORIUM_ONE.getName());
    }
}
