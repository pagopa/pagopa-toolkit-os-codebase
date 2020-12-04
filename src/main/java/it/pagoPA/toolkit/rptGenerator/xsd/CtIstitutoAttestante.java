//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2020.11.24 alle 09:21:33 AM CET 
//


package it.pagoPA.toolkit.rptGenerator.xsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per ctIstitutoAttestante complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ctIstitutoAttestante">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="identificativoUnivocoAttestante" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}ctIdentificativoUnivoco"/>
 *         &lt;element name="denominazioneAttestante" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}stText70"/>
 *         &lt;element name="codiceUnitOperAttestante" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}stText35" minOccurs="0"/>
 *         &lt;element name="denomUnitOperAttestante" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}stText70" minOccurs="0"/>
 *         &lt;element name="indirizzoAttestante" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}stText70" minOccurs="0"/>
 *         &lt;element name="civicoAttestante" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}stText16" minOccurs="0"/>
 *         &lt;element name="capAttestante" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}stText16" minOccurs="0"/>
 *         &lt;element name="localitaAttestante" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}stText35" minOccurs="0"/>
 *         &lt;element name="provinciaAttestante" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}stText35" minOccurs="0"/>
 *         &lt;element name="nazioneAttestante" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}stNazioneProvincia" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ctIstitutoAttestante", propOrder = {
    "identificativoUnivocoAttestante",
    "denominazioneAttestante",
    "codiceUnitOperAttestante",
    "denomUnitOperAttestante",
    "indirizzoAttestante",
    "civicoAttestante",
    "capAttestante",
    "localitaAttestante",
    "provinciaAttestante",
    "nazioneAttestante"
})
public class CtIstitutoAttestante {

    @XmlElement(required = true)
    protected CtIdentificativoUnivoco identificativoUnivocoAttestante;
    @XmlElement(required = true)
    protected String denominazioneAttestante;
    protected String codiceUnitOperAttestante;
    protected String denomUnitOperAttestante;
    protected String indirizzoAttestante;
    protected String civicoAttestante;
    protected String capAttestante;
    protected String localitaAttestante;
    protected String provinciaAttestante;
    protected String nazioneAttestante;

    /**
     * Recupera il valore della proprietà identificativoUnivocoAttestante.
     * 
     * @return
     *     possible object is
     *     {@link CtIdentificativoUnivoco }
     *     
     */
    public CtIdentificativoUnivoco getIdentificativoUnivocoAttestante() {
        return identificativoUnivocoAttestante;
    }

    /**
     * Imposta il valore della proprietà identificativoUnivocoAttestante.
     * 
     * @param value
     *     allowed object is
     *     {@link CtIdentificativoUnivoco }
     *     
     */
    public void setIdentificativoUnivocoAttestante(CtIdentificativoUnivoco value) {
        this.identificativoUnivocoAttestante = value;
    }

    /**
     * Recupera il valore della proprietà denominazioneAttestante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenominazioneAttestante() {
        return denominazioneAttestante;
    }

    /**
     * Imposta il valore della proprietà denominazioneAttestante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenominazioneAttestante(String value) {
        this.denominazioneAttestante = value;
    }

    /**
     * Recupera il valore della proprietà codiceUnitOperAttestante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodiceUnitOperAttestante() {
        return codiceUnitOperAttestante;
    }

    /**
     * Imposta il valore della proprietà codiceUnitOperAttestante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodiceUnitOperAttestante(String value) {
        this.codiceUnitOperAttestante = value;
    }

    /**
     * Recupera il valore della proprietà denomUnitOperAttestante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDenomUnitOperAttestante() {
        return denomUnitOperAttestante;
    }

    /**
     * Imposta il valore della proprietà denomUnitOperAttestante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDenomUnitOperAttestante(String value) {
        this.denomUnitOperAttestante = value;
    }

    /**
     * Recupera il valore della proprietà indirizzoAttestante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIndirizzoAttestante() {
        return indirizzoAttestante;
    }

    /**
     * Imposta il valore della proprietà indirizzoAttestante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIndirizzoAttestante(String value) {
        this.indirizzoAttestante = value;
    }

    /**
     * Recupera il valore della proprietà civicoAttestante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCivicoAttestante() {
        return civicoAttestante;
    }

    /**
     * Imposta il valore della proprietà civicoAttestante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCivicoAttestante(String value) {
        this.civicoAttestante = value;
    }

    /**
     * Recupera il valore della proprietà capAttestante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCapAttestante() {
        return capAttestante;
    }

    /**
     * Imposta il valore della proprietà capAttestante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCapAttestante(String value) {
        this.capAttestante = value;
    }

    /**
     * Recupera il valore della proprietà localitaAttestante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalitaAttestante() {
        return localitaAttestante;
    }

    /**
     * Imposta il valore della proprietà localitaAttestante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalitaAttestante(String value) {
        this.localitaAttestante = value;
    }

    /**
     * Recupera il valore della proprietà provinciaAttestante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProvinciaAttestante() {
        return provinciaAttestante;
    }

    /**
     * Imposta il valore della proprietà provinciaAttestante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProvinciaAttestante(String value) {
        this.provinciaAttestante = value;
    }

    /**
     * Recupera il valore della proprietà nazioneAttestante.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNazioneAttestante() {
        return nazioneAttestante;
    }

    /**
     * Imposta il valore della proprietà nazioneAttestante.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNazioneAttestante(String value) {
        this.nazioneAttestante = value;
    }

}
