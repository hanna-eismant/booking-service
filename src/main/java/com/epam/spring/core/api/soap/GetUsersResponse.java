
package com.epam.spring.core.api.soap;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="usersList" type="{http://epam.com/spring/core/api/soap}soapUser" maxOccurs="unbounded" minOccurs="0"/>
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
    "usersList"
})
@XmlRootElement(name = "getUsersResponse", namespace = "http://epam.com/spring/core/api/soap")
public class GetUsersResponse {

    protected List<SoapUser> usersList;

    /**
     * Gets the value of the usersList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the usersList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getUsersList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link SoapUser }
     * 
     * 
     */
    public List<SoapUser> getUsersList() {
        if (usersList == null) {
            usersList = new ArrayList<SoapUser>();
        }
        return this.usersList;
    }

}
