package com.epam.spring.core.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserService userService;

    @Override
    public Map<String, Object> getUserInfo(final String name) {
        User user = userService.getByName(name);
        int ticketsCount = userService.getBookedTicketsCount(user);

        Map<String, Object> result = new HashMap<>(4);

        result.put("name", user.name);
        result.put("email", user.email);
        result.put("birthday", user.birthday);
        result.put("ticketsCount", ticketsCount);

        return result;
    }

    @Override
    public List<User> getAllUsersInfo() {
        return userService.getAll();
    }
}
