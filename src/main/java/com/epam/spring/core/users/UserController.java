package com.epam.spring.core.users;

import com.epam.spring.core.shared.BookingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private BookingFacade bookingFacade;

    @RequestMapping(method = GET)
    public ModelAndView get() {
        List<User> users = bookingFacade.getAllUsersInfo();

        ModelAndView view = new ModelAndView("users_list");
        view.addObject("users", users);

        return view;
    }

    @RequestMapping(value = "/{name}", method = GET)
    public ModelAndView get(@PathVariable("name") String name) {
        Map<String, Object> userInfo = bookingFacade.getUserInfo(name);

        ModelAndView view = new ModelAndView("users_single");
        view.addAllObjects(userInfo);

        return view;
    }
}
