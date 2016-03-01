package com.epam.spring.core.shared;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import javax.servlet.ServletException;

import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.util.NestedServletException;

import com.epam.spring.core.shared.exceptions.NotFoundException;

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

    @ExceptionHandler(ServletException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ModelAndView onServletException(ServletException e) {
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

    @ExceptionHandler(InternalAuthenticationServiceException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ModelAndView authenticationError(Exception e) {
        e.printStackTrace();
        ModelAndView view = new ModelAndView("500");
        view.addObject("message", e.getMessage());
        return view;
    }
}
