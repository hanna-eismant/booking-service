
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
 *         &lt;element name="show" type="{http://epam.com/spring/core/api/soap}soapShow"/>
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
    "show"
})
@XmlRootElement(name = "getShowResponse", namespace = "http://epam.com/spring/core/api/soap")
public class GetShowResponse {

    @XmlElement(required = true)
    protected SoapShow show;

    /**
     * Gets the value of the show property.
     * 
     * @return
     *     possible object is
     *     {@link SoapShow }
     *     
     */
    public SoapShow getShow() {
        return show;
    }

    /**
     * Sets the value of the show property.
     * 
     * @param value
     *     allowed object is
     *     {@link SoapShow }
     *     
     */
    public void setShow(SoapShow value) {
        this.show = value;
    }

}
