package com.epam.spring.core.users;

import org.springframework.data.repository.CrudRepository;

public interface UserAccountRepository extends CrudRepository<UserAccountEntity, Long> {
}
