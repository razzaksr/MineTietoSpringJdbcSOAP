//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.7 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2024.03.22 at 06:02:21 AM IST 
//


package soap.services;

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
 *         &lt;element name="bankers" type="{http://services.soap}bankers"/>
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
    "bankers"
})
@XmlRootElement(name = "newBankersRequest")
public class NewBankersRequest {

    @XmlElement(required = true)
    protected Bankers bankers;

    /**
     * Gets the value of the bankers property.
     * 
     * @return
     *     possible object is
     *     {@link Bankers }
     *     
     */
    public Bankers getBankers() {
        return bankers;
    }

    /**
     * Sets the value of the bankers property.
     * 
     * @param value
     *     allowed object is
     *     {@link Bankers }
     *     
     */
    public void setBankers(Bankers value) {
        this.bankers = value;
    }

}
