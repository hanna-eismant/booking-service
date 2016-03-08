package com.epam.spring.core.soap.client;

import com.epam.spring.core.soap.client.gen.GetUserInfoRequest;
import com.epam.spring.core.soap.client.gen.GetUserInfoResponse;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

public class WebServiceClient extends WebServiceGatewaySupport {

    public static final String SOAP_URI = "http://localhost:8080/api/soap/getUserInfo";


    public GetUserInfoResponse getUserByName(String name) {
        GetUserInfoRequest request = new GetUserInfoRequest();
        request.setName(name);

        SoapActionCallback callback = new SoapActionCallback(SOAP_URI);
        GetUserInfoResponse response = (GetUserInfoResponse) getWebServiceTemplate().marshalSendAndReceive(request, callback);

        return response;
    }

}
