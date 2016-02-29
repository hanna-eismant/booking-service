package com.epam.spring.core.tickets;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TicketRepository extends CrudRepository<TicketEntity, Long> {

    @Query("SELECT t FROM TicketEntity t WHERE t.user.name = :user_name")
    List<TicketEntity> findByUser(@Param("user_name") String userName);

    @Query("SELECT COUNT(t) FROM TicketEntity t WHERE t.user.name = :user_name")
    Long countByUser(@Param("user_name") String userName);

    @Query("SELECT t FROM TicketEntity t WHERE t.show.id = :show_id ORDER BY t.seat")
    List<TicketEntity> findByShow(@Param("show_id")Long showId);
}
