
package com.epam.spring.core.api.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.epam.spring.core.api.soap package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetUsersRequest_QNAME = new QName("http://epam.com/spring/core/api/soap", "getUsersRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.epam.spring.core.api.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link RegisterUserRequest }
     * 
     */
    public RegisterUserRequest createRegisterUserRequest() {
        return new RegisterUserRequest();
    }

    /**
     * Create an instance of {@link GetUserInfoResponse }
     * 
     */
    public GetUserInfoResponse createGetUserInfoResponse() {
        return new GetUserInfoResponse();
    }

    /**
     * Create an instance of {@link SoapUser }
     * 
     */
    public SoapUser createSoapUser() {
        return new SoapUser();
    }

    /**
     * Create an instance of {@link GetUserInfoRequest }
     * 
     */
    public GetUserInfoRequest createGetUserInfoRequest() {
        return new GetUserInfoRequest();
    }

    /**
     * Create an instance of {@link RegisterUserResponse }
     * 
     */
    public RegisterUserResponse createRegisterUserResponse() {
        return new RegisterUserResponse();
    }

    /**
     * Create an instance of {@link GetUsersResponse }
     * 
     */
    public GetUsersResponse createGetUsersResponse() {
        return new GetUsersResponse();
    }

    /**
     * Create an instance of {@link SoapUserAccount }
     * 
     */
    public SoapUserAccount createSoapUserAccount() {
        return new SoapUserAccount();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://epam.com/spring/core/api/soap", name = "getUsersRequest")
    public JAXBElement<Object> createGetUsersRequest(Object value) {
        return new JAXBElement<Object>(_GetUsersRequest_QNAME, Object.class, null, value);
    }

}
