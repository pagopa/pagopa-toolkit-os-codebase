//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2020.11.24 alle 09:21:33 AM CET 
//


package it.pagoPA.toolkit.rptGenerator.xsd;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per stTipoVersamento.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="stTipoVersamento">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="BBT"/>
 *     &lt;enumeration value="BP"/>
 *     &lt;enumeration value="AD"/>
 *     &lt;enumeration value="CP"/>
 *     &lt;enumeration value="PO"/>
 *     &lt;enumeration value="OBEP"/>
 *     &lt;enumeration value="OTH"/>
 *     &lt;enumeration value="JIF"/>
 *     &lt;enumeration value="MYBK"/>
 *     &lt;maxLength value="4"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "stTipoVersamento")
@XmlEnum
public enum StTipoVersamento {

    BBT,
    BP,
    AD,
    CP,
    PO,
    OBEP,
    OTH,
    JIF,
    MYBK;

    public String value() {
        return name();
    }

    public static StTipoVersamento fromValue(String v) {
        return valueOf(v);
    }

}
