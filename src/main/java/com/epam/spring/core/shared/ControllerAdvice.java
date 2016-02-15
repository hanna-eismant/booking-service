package com.epam.spring.core.shared;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.NestedServletException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ModelAndView notFoundHandler(Exception e) {
        e.printStackTrace();
        ModelAndView view = new ModelAndView("404");
        view.addObject("message", "Page not found");
        return view;
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ModelAndView notFound(Exception e) {
        e.printStackTrace();
        ModelAndView view = new ModelAndView("404");
        view.addObject("message", e.getMessage());
        return view;
    }

    @ExceptionHandler(NestedServletException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ModelAndView internalServletError(Exception e) {
        e.printStackTrace();
        ModelAndView view = new ModelAndView("500");
        view.addObject("message", e.getMessage());
        return view;
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ModelAndView internalError(Exception e) {
        e.printStackTrace();
        ModelAndView view = new ModelAndView("500");
        view.addObject("message", e.getMessage());
        return view;
    }
}
