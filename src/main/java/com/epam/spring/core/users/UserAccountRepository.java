package com.epam.spring.core.users;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserAccountRepository extends CrudRepository<UserAccountEntity, Long> {

    @Modifying
    @Query("UPDATE UserAccountEntity a SET a.money = (a.money - :amount) WHERE a.user.id = :user_id")
    void withdraw(@Param("user_id") Long userId, @Param("amount") Double amount);
}
