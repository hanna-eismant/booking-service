package com.epam.spring.core.soap.client;

import com.epam.spring.core.soap.client.gen.CreateEventRequest;
import com.epam.spring.core.soap.client.gen.CreateEventResponse;
import com.epam.spring.core.soap.client.gen.GetEventRequest;
import com.epam.spring.core.soap.client.gen.GetEventResponse;
import com.epam.spring.core.soap.client.gen.GetEventsResponse;
import com.epam.spring.core.soap.client.gen.GetShowRequest;
import com.epam.spring.core.soap.client.gen.GetShowResponse;
import com.epam.spring.core.soap.client.gen.GetUserInfoRequest;
import com.epam.spring.core.soap.client.gen.GetUserInfoResponse;
import com.epam.spring.core.soap.client.gen.GetUsersResponse;
import com.epam.spring.core.soap.client.gen.ObjectFactory;
import com.epam.spring.core.soap.client.gen.RegisterUserRequest;
import com.epam.spring.core.soap.client.gen.RegisterUserResponse;
import org.joda.time.LocalDate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import javax.xml.bind.JAXBElement;

public class WebServiceClient extends WebServiceGatewaySupport {

    public static final String SOAP_URI = "http://localhost:8080/api/soap";

    private ObjectFactory objectFactory = new ObjectFactory();

    public GetUserInfoResponse getUserByName(String name) {
        GetUserInfoRequest request = objectFactory.createGetUserInfoRequest();
        request.setName(name);

        SoapActionCallback callback = new SoapActionCallback(SOAP_URI);
        GetUserInfoResponse response = (GetUserInfoResponse) getWebServiceTemplate().marshalSendAndReceive(request, callback);

        return response;
    }

    public GetUsersResponse getAllUsers() {
        JAXBElement<Object> request = objectFactory.createGetUsersRequest("");

        SoapActionCallback callback = new SoapActionCallback(SOAP_URI);
        GetUsersResponse response = (GetUsersResponse) getWebServiceTemplate().marshalSendAndReceive(request, callback);

        return response;
    }

    public RegisterUserResponse registerUser(String name, String email, String password, LocalDate birthday) {
        RegisterUserRequest request = objectFactory.createRegisterUserRequest();
        request.setName(name);
        request.setEmail(email);
        request.setPassword(password);
        request.setBirthday(birthday.toString("YYYY-MM-dd"));

        SoapActionCallback callback = new SoapActionCallback(SOAP_URI);
        RegisterUserResponse response = (RegisterUserResponse) getWebServiceTemplate().marshalSendAndReceive(request, callback);

        return response;
    }

    public GetEventsResponse getAllEvents() {
        JAXBElement<Object> request = objectFactory.createGetEventsRequest("");

        SoapActionCallback callback = new SoapActionCallback(SOAP_URI);
        GetEventsResponse response = (GetEventsResponse) getWebServiceTemplate().marshalSendAndReceive(request, callback);

        return response;
    }

    public GetEventResponse getEvent(Long id) {
        GetEventRequest request = objectFactory.createGetEventRequest();
        request.setId(id);

        SoapActionCallback callback = new SoapActionCallback(SOAP_URI);
        GetEventResponse response = (GetEventResponse) getWebServiceTemplate().marshalSendAndReceive(request, callback);

        return response;
    }

    public GetShowResponse getShow(Long id) {
        GetShowRequest request = objectFactory.createGetShowRequest();
        request.setId(id);

        SoapActionCallback callback = new SoapActionCallback(SOAP_URI);
        GetShowResponse response = (GetShowResponse) getWebServiceTemplate().marshalSendAndReceive(request, callback);

        return response;
    }

    public CreateEventResponse createEvent(final String name, final double price, final String rating) {
        CreateEventRequest request = objectFactory.createCreateEventRequest();
        request.setName(name);
        request.setBasePrice(price);
        request.setRating(rating);

        SoapActionCallback callback = new SoapActionCallback(SOAP_URI);
        CreateEventResponse response = (CreateEventResponse) getWebServiceTemplate().marshalSendAndReceive(request, callback);

        return response;
    }

}
