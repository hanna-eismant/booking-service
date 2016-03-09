//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.03.10 at 12:44:53 AM EET 
//


package com.epam.spring.core.soap.client.gen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for soapShow complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="soapShow"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="eventId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="auditorium" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="freeTicketCount" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "soapShow", namespace = "http://epam.com/spring/core/api/soap", propOrder = {
    "id",
    "eventId",
    "date",
    "auditorium",
    "freeTicketCount"
})
public class SoapShow {

    protected long id;
    protected long eventId;
    @XmlElement(required = true)
    protected String date;
    @XmlElement(required = true)
    protected String auditorium;
    protected int freeTicketCount;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Gets the value of the eventId property.
     * 
     */
    public long getEventId() {
        return eventId;
    }

    /**
     * Sets the value of the eventId property.
     * 
     */
    public void setEventId(long value) {
        this.eventId = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDate(String value) {
        this.date = value;
    }

    /**
     * Gets the value of the auditorium property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAuditorium() {
        return auditorium;
    }

    /**
     * Sets the value of the auditorium property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAuditorium(String value) {
        this.auditorium = value;
    }

    /**
     * Gets the value of the freeTicketCount property.
     * 
     */
    public int getFreeTicketCount() {
        return freeTicketCount;
    }

    /**
     * Sets the value of the freeTicketCount property.
     * 
     */
    public void setFreeTicketCount(int value) {
        this.freeTicketCount = value;
    }

}
