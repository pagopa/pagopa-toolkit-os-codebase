//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2020.11.24 alle 09:21:33 AM CET 
//


package it.pagoPA.toolkit.rptGenerator.xsd;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Classe Java per ctDatiSingoloPagamentoRT complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType name="ctDatiSingoloPagamentoRT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="singoloImportoPagato" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}stImporto"/>
 *         &lt;element name="esitoSingoloPagamento" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}stText35" minOccurs="0"/>
 *         &lt;element name="dataEsitoSingoloPagamento" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}stISODate"/>
 *         &lt;element name="identificativoUnivocoRiscossione" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}stText35"/>
 *         &lt;element name="causaleVersamento" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}stText140"/>
 *         &lt;element name="datiSpecificiRiscossione" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}stDatiSpecificiRiscossione"/>
 *         &lt;element name="commissioniApplicatePSP" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}stImporto" minOccurs="0"/>
 *         &lt;element name="commissioniApplicatePA" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}stImportoDiversoDaZero" minOccurs="0"/>
 *         &lt;element name="allegatoRicevuta" type="{http://www.digitpa.gov.it/schemas/2011/Pagamenti/}ctAllegatoRicevuta" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ctDatiSingoloPagamentoRT", propOrder = {
    "singoloImportoPagato",
    "esitoSingoloPagamento",
    "dataEsitoSingoloPagamento",
    "identificativoUnivocoRiscossione",
    "causaleVersamento",
    "datiSpecificiRiscossione",
    "commissioniApplicatePSP",
    "commissioniApplicatePA",
    "allegatoRicevuta"
})
public class CtDatiSingoloPagamentoRT {

    @XmlElement(required = true)
    protected BigDecimal singoloImportoPagato;
    protected String esitoSingoloPagamento;
    @XmlElement(required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar dataEsitoSingoloPagamento;
    @XmlElement(required = true)
    protected String identificativoUnivocoRiscossione;
    @XmlElement(required = true)
    protected String causaleVersamento;
    @XmlElement(required = true)
    protected String datiSpecificiRiscossione;
    protected BigDecimal commissioniApplicatePSP;
    protected BigDecimal commissioniApplicatePA;
    protected CtAllegatoRicevuta allegatoRicevuta;

    /**
     * Recupera il valore della proprietà singoloImportoPagato.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getSingoloImportoPagato() {
        return singoloImportoPagato;
    }

    /**
     * Imposta il valore della proprietà singoloImportoPagato.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setSingoloImportoPagato(BigDecimal value) {
        this.singoloImportoPagato = value;
    }

    /**
     * Recupera il valore della proprietà esitoSingoloPagamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEsitoSingoloPagamento() {
        return esitoSingoloPagamento;
    }

    /**
     * Imposta il valore della proprietà esitoSingoloPagamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEsitoSingoloPagamento(String value) {
        this.esitoSingoloPagamento = value;
    }

    /**
     * Recupera il valore della proprietà dataEsitoSingoloPagamento.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDataEsitoSingoloPagamento() {
        return dataEsitoSingoloPagamento;
    }

    /**
     * Imposta il valore della proprietà dataEsitoSingoloPagamento.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDataEsitoSingoloPagamento(XMLGregorianCalendar value) {
        this.dataEsitoSingoloPagamento = value;
    }

    /**
     * Recupera il valore della proprietà identificativoUnivocoRiscossione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentificativoUnivocoRiscossione() {
        return identificativoUnivocoRiscossione;
    }

    /**
     * Imposta il valore della proprietà identificativoUnivocoRiscossione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentificativoUnivocoRiscossione(String value) {
        this.identificativoUnivocoRiscossione = value;
    }

    /**
     * Recupera il valore della proprietà causaleVersamento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCausaleVersamento() {
        return causaleVersamento;
    }

    /**
     * Imposta il valore della proprietà causaleVersamento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCausaleVersamento(String value) {
        this.causaleVersamento = value;
    }

    /**
     * Recupera il valore della proprietà datiSpecificiRiscossione.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDatiSpecificiRiscossione() {
        return datiSpecificiRiscossione;
    }

    /**
     * Imposta il valore della proprietà datiSpecificiRiscossione.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDatiSpecificiRiscossione(String value) {
        this.datiSpecificiRiscossione = value;
    }

    /**
     * Recupera il valore della proprietà commissioniApplicatePSP.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCommissioniApplicatePSP() {
        return commissioniApplicatePSP;
    }

    /**
     * Imposta il valore della proprietà commissioniApplicatePSP.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCommissioniApplicatePSP(BigDecimal value) {
        this.commissioniApplicatePSP = value;
    }

    /**
     * Recupera il valore della proprietà commissioniApplicatePA.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getCommissioniApplicatePA() {
        return commissioniApplicatePA;
    }

    /**
     * Imposta il valore della proprietà commissioniApplicatePA.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setCommissioniApplicatePA(BigDecimal value) {
        this.commissioniApplicatePA = value;
    }

    /**
     * Recupera il valore della proprietà allegatoRicevuta.
     * 
     * @return
     *     possible object is
     *     {@link CtAllegatoRicevuta }
     *     
     */
    public CtAllegatoRicevuta getAllegatoRicevuta() {
        return allegatoRicevuta;
    }

    /**
     * Imposta il valore della proprietà allegatoRicevuta.
     * 
     * @param value
     *     allowed object is
     *     {@link CtAllegatoRicevuta }
     *     
     */
    public void setAllegatoRicevuta(CtAllegatoRicevuta value) {
        this.allegatoRicevuta = value;
    }

}
