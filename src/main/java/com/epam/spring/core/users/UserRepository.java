package com.epam.spring.core.users;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM UserEntity u WHERE u.name = ?1")
    boolean existsByName(final String name);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM UserEntity u WHERE u.email = ?1")
    boolean existsByEmail(final String email);

    UserEntity findByName(final String name);

    UserEntity findByEmail(final String email);
}
