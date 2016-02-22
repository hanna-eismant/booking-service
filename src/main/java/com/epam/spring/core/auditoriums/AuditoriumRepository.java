package com.epam.spring.core.auditoriums;

import org.springframework.data.repository.CrudRepository;

public interface AuditoriumRepository extends CrudRepository<AuditoriumEntity, Long> {

    AuditoriumEntity findByName(final String name);

}
