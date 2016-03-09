package com.epam.spring.core.api.soap;

import com.epam.spring.core.shared.BookingFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;

@Endpoint
public class EventsEndpoint {

    private static final String NAMESPACE_URI = "http://epam.com/spring/core/api/soap";

    @Autowired
    private BookingFacade bookingFacade;

    private ObjectFactory objectFactory = new ObjectFactory();


//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUsersRequest")
//    @ResponsePayload
//    public GetUsersResponse getUsers() {
//        List<SoapUser> soapUsers = bookingFacade.getAllSoapUsersInfo();
//
//        GetUsersResponse response = objectFactory.createGetUsersResponse();
//        response.getUsersList().addAll(soapUsers);
//
//        return response;
//    }
//
//
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserInfoRequest")
//    @ResponsePayload
//    public GetUserInfoResponse getUserInfo(@RequestPayload GetUserInfoRequest request) throws NotFoundException {
//        String userName = request.getName();
//        SoapUser user = bookingFacade.getSoapUser(userName);
//
//        GetUserInfoResponse response = objectFactory.createGetUserInfoResponse();
//        response.setUser(user);
//
//        return response;
//    }
//
//    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "registerUserRequest")
//    @ResponsePayload
//    public RegisterUserResponse registerUser(@RequestPayload RegisterUserRequest request) {
//        RegisterUserResponse response = objectFactory.createRegisterUserResponse();
//        try {
//            bookingFacade.registerUser(request.getName(), request.getEmail(), request.getPassword(), LocalDate.parse(request.getBirthday()));
//            response.setStatus(true);
//        } catch (Exception e) {
//            response.setStatus(false);
//            response.setMessage(e.getMessage());
//            e.printStackTrace();
//        }
//
//        return response;
//    }
}
