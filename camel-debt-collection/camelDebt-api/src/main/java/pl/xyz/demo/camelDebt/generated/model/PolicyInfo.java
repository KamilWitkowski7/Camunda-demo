package pl.xyz.demo.camelDebt.generated.model;


import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import io.swagger.annotations.ApiModelProperty;


/**
 * <p>Java class for PolicyInfo complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PolicyInfo">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="PolicyNumber" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="SignedDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="PolicyDate" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="BankAccount" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PolicyInfo", propOrder = {
    "policyNumber",
    "signedDate",
    "policyDate",
    "bankAccount"
})
@XmlRootElement(name="Policy")
public class PolicyInfo {
    @ApiModelProperty(example = "12345")
    @XmlElement(name = "PolicyNumber", required = true)
    protected String policyNumber;
    @ApiModelProperty(example = "2019-12-01")
    @XmlElement(name = "SignedDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar signedDate;
    @ApiModelProperty(example = "2019-12-01")
    @XmlElement(name = "PolicyDate", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar policyDate;
    @ApiModelProperty(example = "72249000058430531484853897")
    @XmlElement(name = "BankAccount", required = true)
    protected String bankAccount;

    /**
     * Gets the value of the policyNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPolicyNumber() {
        return policyNumber;
    }

    /**
     * Sets the value of the policyNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPolicyNumber(String value) {
        this.policyNumber = value;
    }

    /**
     * Gets the value of the signedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSignedDate() {
        return signedDate;
    }

    /**
     * Sets the value of the signedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSignedDate(XMLGregorianCalendar value) {
        this.signedDate = value;
    }

    /**
     * Gets the value of the policyDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPolicyDate() {
        return policyDate;
    }

    /**
     * Sets the value of the policyDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPolicyDate(XMLGregorianCalendar value) {
        this.policyDate = value;
    }

    /**
     * Gets the value of the bankAccount property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    public String getBankAccount() {
        return bankAccount;
    }

    /**
     * Sets the value of the bankAccount property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    public void setBankAccount(String value) {
        this.bankAccount = value;
    }


}
