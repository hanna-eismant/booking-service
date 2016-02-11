package com.epam.spring.core.users;

import java.util.List;
import java.util.Map;

public interface UserFacade {

    Map<String, Object> getUserInfo(final String name);

    List<User> getAllUsersInfo();
}
