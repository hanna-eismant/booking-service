package com.epam.spring.core;

import com.epam.spring.core.users.User;
import com.epam.spring.core.users.services.UserService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Date;

public class Runner {

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = new ClassPathXmlApplicationContext("classpath:spring.xml");
        UserService userService = context.getBean(UserService.class);
        User hanna = userService.register("Hanna", "Hanna@Mail", new Date());
    }
}
