package com.epam.spring.core.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;


    public void get() {

    }



}
