package com.epam.spring.core.api.soap;

import com.epam.spring.core.shared.BookingFacade;
import com.epam.spring.core.shared.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI =  "http://epam.com/spring/core/api/soap";

    @Autowired
    private BookingFacade bookingFacade;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserInfo")
    @ResponsePayload
    public GetUserInfoResponse getUserInfo(@RequestPayload GetUserInfoRequest request) throws NotFoundException {
        String userName = request.getName();
        SoapUser user = bookingFacade.getSoapUser(userName);

        GetUserInfoResponse response = new GetUserInfoResponse();
        response.setUser(user);

        return response;
    }



}
