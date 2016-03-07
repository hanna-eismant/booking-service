
package com.epam.spring.core.api.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="user" type="{http://epam.com/spring/core/api/soap}soapUser"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "user"
})
@XmlRootElement(name = "getUserInfoResponse", namespace = "http://epam.com/spring/core/api/soap")
public class GetUserInfoResponse {

    @XmlElement(required = true)
    protected SoapUser user;

    /**
     * Gets the value of the user property.
     * 
     * @return
     *     possible object is
     *     {@link SoapUser }
     *     
     */
    public SoapUser getUser() {
        return user;
    }

    /**
     * Sets the value of the user property.
     * 
     * @param value
     *     allowed object is
     *     {@link SoapUser }
     *     
     */
    public void setUser(SoapUser value) {
        this.user = value;
    }

}
