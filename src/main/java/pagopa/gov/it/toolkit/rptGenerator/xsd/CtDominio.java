//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2020.11.24 alle 09:21:33 AM CET 
//


package pagopa.gov.it.toolkit.rptGenerator.xsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ctDominio complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ctDominio">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identificativoDominio" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}stText35"/>
 *         &lt;element name="identificativoStazioneRichiedente" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}stText35" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ctDominio", propOrder = {
    "identificativoDominio",
    "identificativoStazioneRichiedente"
})
public class CtDominio {

    @XmlElement(required = true)
    protected String identificativoDominio;
    protected String identificativoStazioneRichiedente;

    /**
     * Recupera il valore della proprietà identificativoDominio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoDominio() {
        return identificativoDominio;
    }

    /**
     * Imposta il valore della proprietà identificativoDominio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoDominio(String value) {
        this.identificativoDominio = value;
    }

    /**
     * Recupera il valore della proprietà identificativoStazioneRichiedente.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoStazioneRichiedente() {
        return identificativoStazioneRichiedente;
    }

    /**
     * Imposta il valore della proprietà identificativoStazioneRichiedente.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoStazioneRichiedente(String value) {
        this.identificativoStazioneRichiedente = value;
    }

}
