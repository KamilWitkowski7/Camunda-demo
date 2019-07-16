package pl.xyz.demo.camelDebt.generated.enums;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for LOV_GENDER.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="LOV_GENDER">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="F"/>
 *     &lt;enumeration value="M"/>
 *     &lt;enumeration value="U"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "LOV_GENDER")
@XmlEnum
public enum LOVGENDER {
    F,
    M,
    U;

    public String value() {
        return name();
    }

    public static LOVGENDER fromValue(String v) {
        return valueOf(v);
    }

}
