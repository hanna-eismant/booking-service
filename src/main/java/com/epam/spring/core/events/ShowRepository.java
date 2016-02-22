package com.epam.spring.core.events;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ShowRepository extends CrudRepository<ShowEntity, Long> {

    //    @Query("SELECT COUNT(t.id) FROM shows s LEFT JOIN tickets t ON t.show_id = s.id AND t.user_id IS NULL WHERE s.id = ? GROUP BY s.id ORDER BY")
//    @Query("SELECT COUNT(t) FROM ShowEntity s LEFT JOIN s.tickets t ON t.show.id = :show AND t.user IS NULL WHERE s = :show GROUP BY s.id")
    @Query("SELECT COUNT(t) FROM ShowEntity s LEFT JOIN s.tickets t ON t.show.id = :show_id AND t.user IS NULL")
    int countFreeTickets(@Param(value = "show_id") Long showId);
}


//  SELECT cat FROM Cat cat LEFT JOIN cat.kittens as kitten WITH kitten.owner=:owner
//  SELECT cat FROM Cat cat LEFT JOIN cat.kittens as kitten WITH kitten.owner.ownerId=:ownerId


//       SELECT e FROM Employee e LEFT JOIN e.address ON a.city = :city
//        e.endDate IS NULL


