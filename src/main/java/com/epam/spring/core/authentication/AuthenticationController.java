package com.epam.spring.core.authentication;

import com.epam.spring.core.shared.BookingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
public class AuthenticationController {

    @Autowired
    private BookingFacade bookingFacade;

    @RequestMapping(value = "/login", method = GET)
    public ModelAndView loginView() {
        return new ModelAndView("login");
    }
//
//    @RequestMapping(value = "/login", method = POST)
//    public String loginProcess() {
//
//        // todo: process login
//
//        return "redirect:home";
//    }
}
