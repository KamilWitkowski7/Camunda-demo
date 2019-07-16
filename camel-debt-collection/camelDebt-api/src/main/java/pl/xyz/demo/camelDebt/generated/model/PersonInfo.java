package pl.xyz.demo.camelDebt.generated.model;

import pl.xyz.demo.camelDebt.generated.enums.LOVGENDER;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

import io.swagger.annotations.ApiModelProperty;


/**
 * <p>Java class for PersonInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PersonInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="FirstName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="LastName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Gender" type="{}LOV_GENDER" minOccurs="0"/>
 *         &lt;element name="AddressHome" type="{}Address" minOccurs="0"/>
 *         &lt;element name="PhoneMob" type="{}Phone" minOccurs="0"/>
 *         &lt;element name="EMail" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonInfo", propOrder = {
    "firstName",
    "lastName",
    "gender",
    "addressHome",
    "phoneMob",
    "eMail"
})
@XmlRootElement(name="Person")
public class PersonInfo {
    @ApiModelProperty(example = "Kamil")
    @XmlElement(name = "FirstName", required = true)
    protected String firstName;
    @ApiModelProperty(example = "Witkowski")
    @XmlElement(name = "LastName", required = true)
    protected String lastName;
    @ApiModelProperty(example = "M")
    @XmlElement(name = "Gender")
    @XmlSchemaType(name = "string")
    protected LOVGENDER gender;
    @XmlElement(name = "AddressHome")
    protected Address addressHome;
    @XmlElement(name = "PhoneMob")
    protected Phone phoneMob;
    @XmlElement(name = "EMail")
    protected String eMail;

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the gender property.
     * 
     * @return
     *     possible object is
     *     {@link LOVGENDER }
     *     
     */
    public LOVGENDER getGender() {
        return gender;
    }

    /**
     * Sets the value of the gender property.
     * 
     * @param value
     *     allowed object is
     *     {@link LOVGENDER }
     *     
     */
    public void setGender(LOVGENDER value) {
        this.gender = value;
    }

    /**
     * Gets the value of the addressHome property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    @ApiModelProperty(name = "AddressHome")
    public Address getAddressHome() {
        return addressHome;
    }

    /**
     * Sets the value of the addressHome property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddressHome(Address value) {
        this.addressHome = value;
    }

    /**
     * Gets the value of the phoneMob property.
     * 
     * @return
     *     possible object is
     *     {@link Phone }
     *     
     */
    public Phone getPhoneMob() {
        return phoneMob;
    }

    /**
     * Sets the value of the phoneMob property.
     * 
     * @param value
     *     allowed object is
     *     {@link Phone }
     *     
     */
    public void setPhoneMob(Phone value) {
        this.phoneMob = value;
    }

    /**
     * Gets the value of the eMail property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    @ApiModelProperty(name = "EMail", example = "KamilWitkowski8@gmail.com")
    public String getEMail() {
        return eMail;
    }

    /**
     * Sets the value of the eMail property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEMail(String value) {
        this.eMail = value;
    }

}
