//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2020.11.24 alle 09:21:33 AM CET 
//


package pagopa.gov.it.toolkit.rptGenerator.xsd;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per stTipoAllegatoRicevuta.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * <p>
 * <pre>
 * &lt;simpleType name="stTipoAllegatoRicevuta">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ES"/>
 *     &lt;enumeration value="BD"/>
 *     &lt;length value="2"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "stTipoAllegatoRicevuta")
@XmlEnum
public enum StTipoAllegatoRicevuta {

    ES,
    BD;

    public String value() {
        return name();
    }

    public static StTipoAllegatoRicevuta fromValue(String v) {
        return valueOf(v);
    }

}
